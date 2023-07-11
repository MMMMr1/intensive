package com.jdbc.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "app.doctors")
public class Doctor extends Employee {

    @Column
    private String position;
    @Column
    private String department;

    @OneToMany(mappedBy = "doctor")
    private List<MedicalHistory> patients = new ArrayList<>();


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

    public List<MedicalHistory> getPatients() {
        return patients;
    }

    public void setPatients(List<MedicalHistory> patients) {
        this.patients = patients;
    }
}
