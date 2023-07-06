package com.jdbc.entity;


import java.time.LocalDateTime;
import java.util.UUID;

public class Patient implements Entity {
    private UUID id;
    private String lastName;
    private String firstName;
    private String surName;
    private String address;
    private String phone;
    private String medicalCardNumber;
    private LocalDateTime dtCreated;
    private LocalDateTime dtUpdated;

    public Patient(UUID id, String lastName, String firstName, String surName, String address, String phone, String medicalCardNumber, LocalDateTime dtCreated, LocalDateTime dtUpdated) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.surName = surName;
        this.address = address;
        this.phone = phone;
        this.medicalCardNumber = medicalCardNumber;
        this.dtCreated = dtCreated;
        this.dtUpdated = dtUpdated;
    }

    public Patient() {
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMedicalCardNumber() {
        return medicalCardNumber;
    }

    public void setMedicalCardNumber(String medicalCardNumber) {
        this.medicalCardNumber = medicalCardNumber;
    }

    public  UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getDtCreated() {
        return dtCreated;
    }

    public void setDtCreated(LocalDateTime dtCreated) {
        this.dtCreated = dtCreated;
    }

    public LocalDateTime getDtUpdated() {
        return dtUpdated;
    }

    public void setDtUpdated(LocalDateTime dtUpdated) {
        this.dtUpdated = dtUpdated;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
