package com.prescriptionmanagement.medication_service.payload.response;

import java.util.UUID;

public record MedicationIdResponse(
        UUID id,
        String serialNumber
) {
}
