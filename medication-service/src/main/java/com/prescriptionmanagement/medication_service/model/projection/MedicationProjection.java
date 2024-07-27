package com.prescriptionmanagement.medication_service.model.projection;

import java.util.UUID;

public interface MedicationProjection {
    UUID getId();

    String getSerialNumber();
}
