package com.prescriptionmanagement.prescription_service.client;

import com.prescriptionmanagement.prescription_service.payload.response.client.MedicationIdResponse;
import com.prescriptionmanagement.prescription_service.payload.response.client.MedicationResponse;
import com.prescriptionmanagement.prescription_service.util.Constants;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@FeignClient(
        name = "medication-service",
        path = "/api/v1/medication"
)
public interface MedicationServiceClient {
    Logger log = LoggerFactory.getLogger(MedicationServiceClient.class);

    @GetMapping("/serial-number/{serialNumber}")
    @CircuitBreaker(name = "getBySerialNumberCircuitBreaker", fallbackMethod = "getBySerialNumberFallback")
    ResponseEntity<MedicationIdResponse> getBySerialNumber(@PathVariable @NotBlank String serialNumber);

    default ResponseEntity<MedicationIdResponse> getBySerialNumberFallback(String serialNumber, Exception exception) {
        log.info("Medication not found by serial number: " + serialNumber + " returning default medication object");
        return ResponseEntity.ok(new MedicationIdResponse(
                Constants.DEFAULT_UUID,
                "undefined - serial number"
        ));
    }

    @GetMapping("/id/{id}")
    @CircuitBreaker(name = "getByIdCircuitBreaker", fallbackMethod = "getByIdFallBack")
    ResponseEntity<MedicationResponse> getById(@PathVariable UUID id);

    default ResponseEntity<MedicationResponse> getByIdFallBack(UUID id, Exception exception) {
        log.info("Medication not by id: " + id + " returning default medication object");
        return ResponseEntity.ok(
                new MedicationResponse(
                        new MedicationIdResponse(Constants.DEFAULT_UUID, "undefined - serial number"),
                        "undefined - name",
                        "undefined - manufacturer",
                        false
                ));
    }
}