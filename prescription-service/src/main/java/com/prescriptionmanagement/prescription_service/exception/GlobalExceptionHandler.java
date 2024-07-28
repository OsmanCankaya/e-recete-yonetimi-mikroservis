package com.prescriptionmanagement.prescription_service.exception;

import com.prescriptionmanagement.prescription_service.payload.response.error.ErrorResponse;
import com.prescriptionmanagement.prescription_service.payload.response.error.ValidationErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PrescriptionNotFoundException.class)
    public ResponseEntity<?> handlePrescriptionNotFoundException(PrescriptionNotFoundException ex) {
        HttpStatus notFoundStatus = HttpStatus.NOT_FOUND;
        ErrorResponse errorResponse = new ErrorResponse(notFoundStatus.value(), "Prescription not found");
        return ResponseEntity.status(notFoundStatus).body(errorResponse);
    }

    @ExceptionHandler(PrescriptionNoAlreadyExistsException.class)
    public ResponseEntity<?> handlePrescriptionNoAlreadyExistsException(PrescriptionNoAlreadyExistsException ex) {
        HttpStatus conflictStatus = HttpStatus.CONFLICT;
        ErrorResponse errorResponse = new ErrorResponse(conflictStatus.value(), "Prescription no value already exist");
        return ResponseEntity.status(conflictStatus).body(errorResponse);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolation(ConstraintViolationException ex) {
        HttpStatus badRequestStatus = HttpStatus.BAD_REQUEST;
        Map<String, String> errorMessages = getValidaitonErrorMessageList(ex.getConstraintViolations());
        ValidationErrorResponse errorResponse = ValidationErrorResponse.builder()
                .statusCode(badRequestStatus.value())
                .message("Validation Error")
                .errors(errorMessages)
                .build();
        return ResponseEntity.status(badRequestStatus).body(errorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        status = HttpStatus.BAD_REQUEST;
        Map<String, String> errorMessages = getFildErrorMessagesMap(ex.getFieldErrors(), ex.getGlobalErrors());
        ValidationErrorResponse errorResponse = ValidationErrorResponse.builder()
                .statusCode(status.value())
                .message("Validation error. Check 'errors' field for details.")
                .errors(errorMessages)
                .build();
        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        HttpStatus badRequestStatus = HttpStatus.BAD_REQUEST;
        HashMap<String, String> errorMessages = new HashMap<>();
        errorMessages.put(ex.getParameter() + "", ex.getName() + "' with value '" + ex.getValue() + "' is not of the expected TYPE");
        ValidationErrorResponse validationErrorResponse = ValidationErrorResponse.builder()
                .statusCode(badRequestStatus.value())
                .message("Validation Error")
                .errors(errorMessages)
                .build();
        return ResponseEntity.status(badRequestStatus).body(validationErrorResponse);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<?> handleGenericException(Exception exception) {
        log.error("Generic Exception: " + exception.getMessage(), exception);
        HttpStatus interServerErrorStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse = new ErrorResponse(interServerErrorStatus.value(), "An error occurred");
        return ResponseEntity.status(interServerErrorStatus).body(errorResponse);
    }

    private Map<String, String> getFildErrorMessagesMap(List<FieldError> fieldErrors, List<ObjectError> objectErrors) {
        Map<String, String> errorMessages = new HashMap<>();
        //populate errorMessages hashmap
        fieldErrors.forEach((fieldError) ->
                errorMessages.put(fieldError.getField(), fieldError.getDefaultMessage())
        );
        objectErrors.forEach((objectError) ->
                errorMessages.put(objectError.getObjectName(), objectError.getDefaultMessage())
        );
        return errorMessages;
    }

    private Map<String, String> getValidaitonErrorMessageList(Set<ConstraintViolation<?>> violations) {
        if (violations.isEmpty())
            return null;
        Map<String, String> validationErrorMessages = new HashMap<>();
        violations.forEach(cv -> {
            validationErrorMessages.put("validaitonMessage",
                    cv.getMessage() + " - " + "invalidValue: " +
                            (cv.getInvalidValue() == null ? "null" : cv.getInvalidValue().toString())
            );
        });
        return validationErrorMessages;
    }
}
