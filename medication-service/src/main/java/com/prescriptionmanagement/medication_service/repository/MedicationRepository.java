package com.prescriptionmanagement.medication_service.repository;

import com.prescriptionmanagement.medication_service.model.entity.Medication;
import com.prescriptionmanagement.medication_service.model.projection.MedicationProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MedicationRepository extends JpaRepository<Medication, UUID> {
    Optional<MedicationProjection> findMedicationProjectionBySerialNumber(String serialNumber);
}
