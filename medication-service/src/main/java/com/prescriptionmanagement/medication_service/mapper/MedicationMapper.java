package com.prescriptionmanagement.medication_service.mapper;

import com.prescriptionmanagement.medication_service.model.entity.Medication;
import com.prescriptionmanagement.medication_service.model.projection.MedicationProjection;
import com.prescriptionmanagement.medication_service.payload.response.MedicationIdResponse;
import com.prescriptionmanagement.medication_service.payload.response.MedicationResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MedicationMapper {
    List<MedicationResponse> mapToMedicationResponses(List<Medication> medications);

    @Mapping(source = "id", target = "medicationId.id")
    @Mapping(source = "serialNumber", target = "medicationId.serialNumber")
    MedicationResponse mapToMedicationResponse(Medication medication);

    MedicationIdResponse mapToMedicationIdResponse(MedicationProjection medicationProjection);
}
