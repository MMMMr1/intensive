package com.jdbc.dto.doctor;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class DoctorEditDto {
    @JsonProperty("uuid")
    private UUID uuid;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("surName")
    private String surName;
    @JsonProperty("position")
    private String position;
    @JsonProperty("department")
    private String department;

    public DoctorEditDto() {
    }

    public DoctorEditDto(UUID uuid, String lastName, String firstName, String surName, String position, String department) {
        this.uuid = uuid;
        this.lastName = lastName;
        this.firstName = firstName;
        this.surName = surName;
        this.position = position;
        this.department = department;
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

    @Override
    public String toString() {
        return "DoctorEditDto{" +
                "uuid=" + uuid +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                ", position='" + position + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
