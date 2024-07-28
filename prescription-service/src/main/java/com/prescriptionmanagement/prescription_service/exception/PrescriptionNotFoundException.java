package com.prescriptionmanagement.prescription_service.exception;

public class PrescriptionNotFoundException extends RuntimeException {

    public PrescriptionNotFoundException(String s) {
        super(s);
    }
}
