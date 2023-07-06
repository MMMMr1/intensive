package com.jdbc.dto.doctor;



import java.time.LocalDateTime;
import java.util.UUID;

public class DoctorReadDto {
    private UUID id;
    private String lastName;
    private String firstName;
    private String surName;
    private String position;
    private String department;
    private LocalDateTime dtCreated;
    private LocalDateTime dtUpdated;

    public DoctorReadDto() {
    }

    public DoctorReadDto(UUID id, String lastName, String firstName, String surName, String position, String department, LocalDateTime dtCreated, LocalDateTime dtUpdated) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.surName = surName;
        this.position = position;
        this.department = department;
        this.dtCreated = dtCreated;
        this.dtUpdated = dtUpdated;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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
}
