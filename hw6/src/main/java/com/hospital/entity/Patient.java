package com.hospital.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "app", name = "patients")
public class Patient {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "surname")
    private String surName;
    @Column
    private String address;
    @Column
    private String phone;
    @Column(name = "medical_card_number")
    private String medicalCardNumber;
    @OneToMany(mappedBy = "patient",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set <MedicalHistory> medicalHistories = new HashSet<>();
    @Column(name = "dt_created")
    private Instant dtCreated;
    @Column(name = "dt_updated")
    private Instant dtUpdated;
}
