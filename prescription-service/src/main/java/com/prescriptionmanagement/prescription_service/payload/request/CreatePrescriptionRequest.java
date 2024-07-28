package com.prescriptionmanagement.prescription_service.payload.request;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

@Schema(description = "Represents a data transfer object to create a prescription")
public record CreatePrescriptionRequest(
        @NotNull
        @Schema(description = "The prescription number for the new prescription. This field must be unique.")
        String prescriptionNo,

        @NotNull
        @Schema(description = " The name of the doctor who issued the prescription.", example = "Dr. Mehmet Öz")
        String doctorName
) {
}
