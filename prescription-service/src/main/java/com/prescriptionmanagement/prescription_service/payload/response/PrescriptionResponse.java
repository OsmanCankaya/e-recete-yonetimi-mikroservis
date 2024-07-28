package com.prescriptionmanagement.prescription_service.payload.response;

import com.prescriptionmanagement.prescription_service.payload.response.client.MedicationResponse;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Schema(description = "Prescription Response object containing details about a prescription")
public record PrescriptionResponse(
        @NotNull
        @Schema(description = "The unique identifier of the prescription.")
        UUID id,

        @NotNull
        @Schema(description = "The prescription number associated with the prescription. This field must be unique.")
        String prescriptionNo,

        @NotNull
        @Schema(description = " The name of the doctor who issued the prescription.", example = "Dr. Mehmet Öz")
        String doctorName,

        @Schema(description = "The list of medications prescribed (can be empty {}, in which case an empty list is assigned).")
        List<MedicationResponse> medications) {

    public PrescriptionResponse {
        if (medications == null) {
            medications = List.of();
        }
    }
}
