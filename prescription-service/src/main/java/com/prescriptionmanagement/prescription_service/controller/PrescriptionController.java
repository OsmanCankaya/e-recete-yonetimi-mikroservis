package com.prescriptionmanagement.prescription_service.controller;

import com.prescriptionmanagement.prescription_service.payload.request.AddMedicationRequest;
import com.prescriptionmanagement.prescription_service.payload.request.CreatePrescriptionRequest;
import com.prescriptionmanagement.prescription_service.payload.response.AddMedicationResponse;
import com.prescriptionmanagement.prescription_service.payload.response.PrescriptionResponse;
import com.prescriptionmanagement.prescription_service.payload.response.error.ErrorResponse;
import com.prescriptionmanagement.prescription_service.payload.response.error.ValidationErrorResponse;
import com.prescriptionmanagement.prescription_service.service.PrescriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/prescription")
@RequiredArgsConstructor
@Validated
@Slf4j
@Tag(name = "Prescription Management", description = "It related to creating and managing prescriptions")
public class PrescriptionController {
    private final PrescriptionService prescriptionService;
    private final Environment environment;

    @PostMapping
    @Operation(summary = "Create a new prescription.",
            description = "Creates a new prescription with the specified details.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Prescription created successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PrescriptionResponse.class),
                                    examples = @ExampleObject("{\n" +
                                            "    \"id\": \"521e0d20-1327-4993-960a-46c2fc1c1362\",\n" +
                                            "    \"prescriptionNo\": \"C-21\",\n" +
                                            "    \"doctorName\": \"Dr. Mehmet Öz\",\n" +
                                            "    \"medications\": []\n" +
                                            "}"))
                    ),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "409", description = "Prescription no value already exist",
                            content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid Input Data",
                            content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class)))
            })
    public ResponseEntity<PrescriptionResponse> createPrescription(
            @Valid
            @RequestBody
            @Parameter(description = "Values for Prescription you need to create", required = true)
            CreatePrescriptionRequest createPrescriptionRequest) {
        return ResponseEntity.ok(prescriptionService.createPrescription(createPrescriptionRequest));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a Prescription by ID",
            description = "Retrieves details of a prescription by its ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "prescription found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = PrescriptionResponse.class),
                                    examples = @ExampleObject("{\n" +
                                            "    \"id\": \"25697478-6e2a-430b-8aeb-932edecbe25e\",\n" +
                                            "    \"prescriptionNo\": \"C-2111\",\n" +
                                            "    \"doctorName\": \"Dr. Mehmet Öz\",\n" +
                                            "    \"medications\": []\n" +
                                            "}"))
                    ),
                    @ApiResponse(responseCode = "404", description = "Parking area not found",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid Input Data",
                            content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            })
    public ResponseEntity<PrescriptionResponse> getByPrescriptionById(
            @PathVariable
            @Parameter(description = "ID value for the prescription you need to retrieve", required = true, example = "25697478-6e2a-430b-8aeb-932edecbe25e")
            UUID id) {
        return ResponseEntity.ok(prescriptionService.getByID(id));
    }

    @PutMapping
    @Operation(summary = "Add Medication to Prescription",
            description = "Updates a prescription by adding medication",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Medication added successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = AddMedicationResponse.class),
                                    examples = @ExampleObject("{\n" +
                                            "    \"id\": \"fc6e18e4-7f8d-40e0-978f-ac67994d5dd9\"\n" +
                                            "}"))
                    ),
                    @ApiResponse(responseCode = "404", description = "Parking area not found",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid Input Data",
                            content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Internal Server Error",
                            content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class)))
            })
    public ResponseEntity<AddMedicationResponse> addMedicationToPrescription(
            @RequestBody
            @Valid
            @Parameter(description = "prescription ID and medication serial number values for the adding medication to prescription", required = true)
            AddMedicationRequest addMedicationRequest) {
        AddMedicationResponse response = prescriptionService.addMedicationToPrescription(addMedicationRequest);
        return ResponseEntity.ok(response);
    }
}