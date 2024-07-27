package com.prescriptionmanagement.medication_service.service;

import com.prescriptionmanagement.medication_service.exception.MedicationNotFoundException;
import com.prescriptionmanagement.medication_service.mapper.MedicationMapper;
import com.prescriptionmanagement.medication_service.payload.response.MedicationIdResponse;
import com.prescriptionmanagement.medication_service.payload.response.MedicationResponse;
import com.prescriptionmanagement.medication_service.repository.MedicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MedicationService {
    private final MedicationRepository repository;
    private final MedicationMapper mapper;

    public MedicationResponse getById(UUID id) {
        return repository
                .findById(id)
                .map(mapper::mapToMedicationResponse)
                .orElseThrow(() -> new MedicationNotFoundException("Medication not found by id: " + id));
    }

    public List<MedicationResponse> getAllMedications() {
        return mapper.mapToMedicationResponses(repository.findAll());
    }

    public MedicationIdResponse getBySerialNumber(String serialNumber) {
        return repository
                .findMedicationProjectionBySerialNumber(serialNumber)
                .map(mapper::mapToMedicationIdResponse)
                .orElseThrow(() -> new MedicationNotFoundException("Medication not found by serialNumber: " + serialNumber));
    }
}
