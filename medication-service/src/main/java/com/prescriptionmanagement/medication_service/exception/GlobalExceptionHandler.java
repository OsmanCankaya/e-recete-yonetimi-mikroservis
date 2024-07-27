package com.prescriptionmanagement.medication_service.exception;

import com.prescriptionmanagement.medication_service.payload.response.error.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.*;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MedicationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handleMedicationNotFound(MedicationNotFoundException ex) {
        log.error("MedicationNotFoundException: {}", ex.getMessage(), ex);
        HttpStatus notFoundStatus = HttpStatus.NOT_FOUND;
        ErrorResponse errorResponse = new ErrorResponse(notFoundStatus.value(), "Medication not found");
        return ResponseEntity.status(notFoundStatus).body(errorResponse);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleConstraintViolation(ConstraintViolationException ex) {
        log.error("MethodArgumentTypeMismatchException : {}", ex.getMessage(), ex);
        HttpStatus badRequestStatus = HttpStatus.BAD_REQUEST;
        ErrorResponse errorResponse = new ErrorResponse(badRequestStatus.value(), "Validation Error");
        errorResponse.addProperty("errors: ", getValidaitonErrorMessageList(ex.getConstraintViolations()));
        return ResponseEntity.status(badRequestStatus).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        HttpStatus badRequestStatus = HttpStatus.BAD_REQUEST;
        String message = "Parameter '" + ex.getName() + "' with value '" + ex.getValue() + "' is not of the expected TYPE";
        ErrorResponse errorResponse = new ErrorResponse(badRequestStatus.value(), message);
        return ResponseEntity.status(badRequestStatus).body(errorResponse);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<?> handleGenericException(Exception exception, WebRequest request) {
        log.error("Generic Exception: " + exception.getMessage(), exception);
        HttpStatus interServerErrorStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse errorResponse = new ErrorResponse(interServerErrorStatus.value(), "An error occurred");
        return ResponseEntity.status(interServerErrorStatus).body(errorResponse);
    }

    private List<Map<String, String>> getValidaitonErrorMessageList(Set<ConstraintViolation<?>> violations) {
        if (violations.isEmpty())
            return null;
        List<Map<String, String>> validationErrorMessages = new ArrayList<>();
        violations.forEach(cv -> {
            Map<String, String> validationmessageMap = new HashMap<>();
            validationmessageMap.put("validaitonMessage", cv.getMessage());
            validationmessageMap.put("invalidValue",
                    cv.getInvalidValue() == null ? "null" : cv.getInvalidValue().toString());
            validationErrorMessages.add(validationmessageMap);
        });
        return validationErrorMessages;
    }
}
