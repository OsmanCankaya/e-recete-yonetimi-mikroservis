package com.prescriptionmanagement.prescription_service.service;

import com.prescriptionmanagement.prescription_service.client.MedicationServiceClient;
import com.prescriptionmanagement.prescription_service.exception.PrescriptionNoAlreadyExistsException;
import com.prescriptionmanagement.prescription_service.exception.PrescriptionNotFoundException;
import com.prescriptionmanagement.prescription_service.mapper.PrescriptionMapper;
import com.prescriptionmanagement.prescription_service.model.entity.Prescription;
import com.prescriptionmanagement.prescription_service.payload.request.AddMedicationRequest;
import com.prescriptionmanagement.prescription_service.payload.request.CreatePrescriptionRequest;
import com.prescriptionmanagement.prescription_service.payload.response.AddMedicationResponse;
import com.prescriptionmanagement.prescription_service.payload.response.PrescriptionResponse;
import com.prescriptionmanagement.prescription_service.payload.response.client.MedicationResponse;
import com.prescriptionmanagement.prescription_service.repository.PrescriptionRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrescriptionService {
    private final PrescriptionRespository respository;
    private final PrescriptionMapper mapper;
    private final MedicationServiceClient medicationServiceClient;

    public PrescriptionResponse getByID(UUID id) {
        //getting prescription from db
        Prescription prescription = respository.findById(id)
                .orElseThrow(() ->
                        new PrescriptionNotFoundException("Prescription not found by id:" + id));
        //fecthing medications detail via feign client
        List<MedicationResponse> medicationResponses = prescription.getMedications().stream().map(uuid ->
                medicationServiceClient.getById(uuid).getBody()).collect(Collectors.toList());

        PrescriptionResponse prescriptionResponse = mapper.prescriptionToPrescriptionResponse(prescription, medicationResponses);
        return prescriptionResponse;
    }

    public PrescriptionResponse createPrescription(CreatePrescriptionRequest createPrescriptionRequest) {
        Prescription prescription = mapper.mapToPrescription(createPrescriptionRequest);
        if (respository.existsByPrescriptionNo(prescription.getPrescriptionNo()))
            throw new PrescriptionNoAlreadyExistsException();
        Prescription prescriptionSaved = respository.save(prescription);
        return mapper.mapToPrescriptionResponse(prescriptionSaved);
    }

    public AddMedicationResponse addMedicationToPrescription(AddMedicationRequest request) {
        UUID medicationId = medicationServiceClient.getBySerialNumber(request.medicationSerialNumber()).getBody().id();
        Prescription prescription = respository
                .findById(request.prescriptionId())
                .orElseThrow(() ->
                        new PrescriptionNotFoundException("Prescription not found by id:" + request.prescriptionId()));
        prescription.getMedications().add(medicationId);
        Prescription prescriptionSaved = respository.save(prescription);
        return new AddMedicationResponse(prescriptionSaved.getId());
    }
}
