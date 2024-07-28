package com.prescriptionmanagement.prescription_service.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Schema(description = "Add Medication Request object containing details for adding medication to a prescription.")
public record AddMedicationRequest(
        @NotNull
        @Schema(description = " The unique identifier of the prescription to which medication will be added.")
        UUID prescriptionId,
        @NotNull
        @Schema(description = "The serial number of the medication to be added.")
        String medicationSerialNumber
) {
}
