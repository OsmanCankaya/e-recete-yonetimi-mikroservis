package com.prescriptionmanagement.medication_service.config;

import com.prescriptionmanagement.medication_service.model.entity.Medication;
import com.prescriptionmanagement.medication_service.repository.MedicationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class DbInitializer implements CommandLineRunner {

    private final MedicationRepository medicationRepository;

    @Override
    public void run(String... args) {
        Medication medication1 = Medication.builder()
                .serialNumber("Aspirin-101")
                .name("Aspirin")
                .prescriptionRequired(false)
                .manufacturer("Bayer")
                .build();
        Medication medication2 = Medication.builder()
                .serialNumber("Parasetamol-102")
                .name("Parasetamol")
                .prescriptionRequired(true)
                .manufacturer("Tylenol")
                .build();
        Medication medication3 = Medication.builder()
                .serialNumber("TylolHot-103")
                .name("TylolHot")
                .prescriptionRequired(false)
                .manufacturer("R&D")
                .build();
        List<Medication> addedMedications = medicationRepository.saveAll(Arrays.asList(medication1, medication2, medication3));
        log.info(addedMedications.toString());
    }
}
