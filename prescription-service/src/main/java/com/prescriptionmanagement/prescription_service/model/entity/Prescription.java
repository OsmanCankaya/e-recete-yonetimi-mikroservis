package com.prescriptionmanagement.prescription_service.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Prescription extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String prescriptionNo;

    @Column(nullable = false)
    private String doctorName;

    @ElementCollection
    private List<UUID> medications = new ArrayList<>();
}
