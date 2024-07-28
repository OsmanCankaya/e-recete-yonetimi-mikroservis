package com.prescriptionmanagement.prescription_service.payload.response.error;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Map;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Validation Error Response Object")
public class ValidationErrorResponse extends ErrorResponse {

    @Schema(description = "validation errors")
    private Map<String, String> errors;
}
