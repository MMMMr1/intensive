package com.jdbc.dto.patient;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class PatientEditDto {
    @JsonProperty("uuid")
    private UUID uuid;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("surName")
    private String surName;
    @JsonProperty("address")
    private String address;
    @JsonProperty("phone")
    private String phone;
    @JsonProperty("medicalCardNumber")
    private String medicalCardNumber;

    public PatientEditDto() {
    }

    public PatientEditDto(UUID uuid, String lastName, String firstName, String surName, String address, String phone, String medicalCardNumber) {
        this.uuid = uuid;
        this.lastName = lastName;
        this.firstName = firstName;
        this.surName = surName;
        this.address = address;
        this.phone = phone;
        this.medicalCardNumber = medicalCardNumber;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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



    @Override
    public String toString() {
        return "Patient{" +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", medicalCardNumber='" + medicalCardNumber + '\'' +
                '}';
    }
}
