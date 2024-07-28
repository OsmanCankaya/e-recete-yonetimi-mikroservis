package com.prescriptionmanagement.prescription_service.repository;

import com.prescriptionmanagement.prescription_service.model.entity.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PrescriptionRespository extends JpaRepository<Prescription, UUID> {
    boolean existsByPrescriptionNo(String prescriptionNo);
}
