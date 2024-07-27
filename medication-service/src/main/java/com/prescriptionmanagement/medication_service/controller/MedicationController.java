package com.prescriptionmanagement.medication_service.controller;

import com.prescriptionmanagement.medication_service.payload.response.MedicationIdResponse;
import com.prescriptionmanagement.medication_service.payload.response.MedicationResponse;
import com.prescriptionmanagement.medication_service.service.MedicationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/medication")
@RequiredArgsConstructor
@Validated
@Slf4j
public class MedicationController {
    private final MedicationService medicationService;

    @GetMapping
    public ResponseEntity<List<MedicationResponse>> getAll() {
        return ResponseEntity.ok(medicationService.getAllMedications());
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<MedicationResponse> getById(@PathVariable(value = "id") UUID id) {
        return ResponseEntity.ok(medicationService.getById(id));
    }

    @GetMapping("/serial-number/{serialNumber}")
    public ResponseEntity<MedicationIdResponse> getBySerialNumber(
            @PathVariable(value = "serialNumber")
            @NotBlank
            String serialNumber) {
        log.info("medication requested by isbn: " + serialNumber);
        return ResponseEntity.ok(medicationService.getBySerialNumber(serialNumber));
    }
}
