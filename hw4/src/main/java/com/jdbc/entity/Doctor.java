package com.jdbc.entity;

import javax.persistence.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;


import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "app.doctors")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Doctor extends Employee {
    @Column
    private String position;
    @Column
    private String department;
    @Column
    private Integer workHours;
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "doctor",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<MedicalHistory> patients = new ArrayList<>();
    public Doctor() {
    }
    public Doctor(Long uuid,
                  String lastName,
                  String firstName,
                  String surName,
                  String position,
                  String department,
                  Integer workHours,
                  LocalDateTime dtCreated,
                  LocalDateTime dtUpdated) {
        super(uuid,lastName,firstName,surName,dtCreated,dtUpdated);
        this.position = position;
        this.department = department;
        this.workHours = workHours;
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
    public Integer getWorkHours() {
        return workHours;
    }
    public void setWorkHours(Integer workHours) {
        this.workHours = workHours;
    }
}