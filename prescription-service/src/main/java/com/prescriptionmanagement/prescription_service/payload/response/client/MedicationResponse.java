package com.prescriptionmanagement.prescription_service.payload.response.client;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

@Schema(description = "Represents a Medication Response object containing details about a medication.")
public record MedicationResponse(
        @NotNull
        @Schema(description = "The identifier object of the medication.")
        MedicationIdResponse medicationId,

        @NotNull
        @Schema(description = "The name of the medication.")
        String name,

        @Schema(description = "The manufacturer of the medication.")
        String manufacturer,

        @NotNull
        @Schema(description = "Indicates whether a prescription is required for this medication.")
        boolean prescriptionRequired) {
}