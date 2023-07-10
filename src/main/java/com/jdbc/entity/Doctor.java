package com.jdbc.entity;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "app.doctors")
public class Doctor extends Employee {

    @Column
    private String position;
    @Column
    private String department;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            schema = "app",
            name = "doctors_patients",
            joinColumns = { @JoinColumn(name = "doctor_id") },
            inverseJoinColumns = { @JoinColumn(name = "patient_uuid") }
    )
    private Set<Patient> patients;


    public Doctor() {
    }

    public Doctor(Long uuid, String lastName, String firstName, String surName, String position, String department, LocalDateTime dtCreated, LocalDateTime dtUpdated) {
        super(uuid,lastName,firstName,surName,dtCreated,dtUpdated);
        this.position = position;
        this.department = department;
    }


    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }

}
