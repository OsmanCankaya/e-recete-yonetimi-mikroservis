package com.prescriptionmanagement.medication_service.model.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@ToString(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Medication extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String serialNumber;

    @Column(nullable = false)
    private String name;

    private String manufacturer;

    @Column(columnDefinition = "bit default 1")
    @Builder.Default
    private boolean prescriptionRequired = true;
}
