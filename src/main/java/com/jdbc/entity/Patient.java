package com.jdbc.entity;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Table(name = "app.patients")
public class Patient {
    @Id
    private UUID uuid;
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
    @Column(name = "dt_created")
    private LocalDateTime dtCreated;
    @Column(name = "dt_updated")
    private LocalDateTime dtUpdated;

    public Patient(UUID uuid, String lastName, String firstName, String surName, String address, String phone, String medicalCardNumber, LocalDateTime dtCreated, LocalDateTime dtUpdated) {
        this.uuid = uuid;
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
        return uuid;
    }

    public void setId(UUID uuid) {
        this.uuid = uuid;
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
