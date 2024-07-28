package com.prescriptionmanagement.prescription_service.payload.response.client;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Schema(description = "A Medication ID Response object containing identifiers for a medication.")
public record MedicationIdResponse(

        @NotNull
        @Schema(description = "The unique identifier of the medication.")
        UUID id,

        @NotNull
        @Schema(description = "The serial number associated with the medication.")
        String serialNumber
) {
}