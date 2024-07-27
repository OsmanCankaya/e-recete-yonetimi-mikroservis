package com.prescriptionmanagement.medication_service.payload.response;

public record MedicationResponse(
        MedicationIdResponse medicationId,
        String name,
        String manufacturer,
        boolean prescriptionRequired) {
}