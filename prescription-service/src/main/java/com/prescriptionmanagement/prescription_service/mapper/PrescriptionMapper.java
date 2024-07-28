package com.prescriptionmanagement.prescription_service.mapper;

import com.prescriptionmanagement.prescription_service.model.entity.Prescription;
import com.prescriptionmanagement.prescription_service.payload.request.CreatePrescriptionRequest;
import com.prescriptionmanagement.prescription_service.payload.response.PrescriptionResponse;
import com.prescriptionmanagement.prescription_service.payload.response.client.MedicationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface PrescriptionMapper {
    List<PrescriptionResponse> mapToPrescriptionResponses(List<Prescription> prescriptions);

    Prescription mapToPrescription(CreatePrescriptionRequest createPrescriptionRequest);

    PrescriptionResponse mapToPrescriptionResponse(Prescription prescription);

    @Mapping(source = "uuid", target = "medicationId.id")
    MedicationResponse mapToMedicationResponse(UUID uuid);

    // to including medications
    @Mapping(target = "medications", source = "medications")
    PrescriptionResponse prescriptionToPrescriptionResponse(Prescription prescription, List<MedicationResponse> medications);
}
