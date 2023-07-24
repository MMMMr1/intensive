package com.jdbc.dto.doctor;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DoctorCreateDto {
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



    public DoctorCreateDto() {
    }
    public DoctorCreateDto(String lastName, String firstName, String surName, String position, String department) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.surName = surName;
        this.position = position;
        this.department = department;
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
        return "DoctorCreateDto{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                ", position='" + position + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
