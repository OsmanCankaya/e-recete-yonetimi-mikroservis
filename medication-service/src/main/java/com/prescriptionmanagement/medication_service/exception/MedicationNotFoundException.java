package com.prescriptionmanagement.medication_service.exception;

public class MedicationNotFoundException extends RuntimeException {

    public MedicationNotFoundException(String s) {
        super(s);
    }
}
