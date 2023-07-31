package com.hospital.entity;


import javax.persistence.*;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Entity
@Table(schema = "app", name = "employees")
@Inheritance(strategy = InheritanceType.JOINED)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "surname")
    private String surName;
    @Column
    private String position;
    @Column
    private String department;
    @Column(name = "dt_created")
    private Instant dtCreated;
    @Column(name = "dt_updated")
    private Instant dtUpdated;

  }
