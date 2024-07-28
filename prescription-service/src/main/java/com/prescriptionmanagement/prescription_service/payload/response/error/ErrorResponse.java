package com.prescriptionmanagement.prescription_service.payload.response.error;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "ErrorResponse", description = "Error Response Object")
public class ErrorResponse {

    @NotNull
    @Schema(description = "HTTP Status Code", required = true)
    private int statusCode;

    @Schema(description = "Error message")
    private String message;
}
