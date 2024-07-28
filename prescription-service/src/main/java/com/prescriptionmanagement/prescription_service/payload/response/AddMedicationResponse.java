package com.prescriptionmanagement.prescription_service.payload.response;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Schema(description = "Response DTO for adding medication to prescription")
public record AddMedicationResponse(
        @NotNull
        @Schema(description = "The unique identifier of the prescription.")
        UUID id
) {
}
