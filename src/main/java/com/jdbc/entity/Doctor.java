package com.jdbc.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "app.doctors")
public class Doctor {
    @Id
    private UUID uuid;
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
    private LocalDateTime dtCreated;
    @Column(name = "dt_updated")
    private LocalDateTime dtUpdated;

    public Doctor() {
    }

    public Doctor(UUID uuid, String lastName, String firstName, String surName, String position, String department, LocalDateTime dtCreated, LocalDateTime dtUpdated) {
        this.uuid = uuid;
        this.lastName = lastName;
        this.firstName = firstName;
        this.surName = surName;
        this.position = position;
        this.department = department;
        this.dtCreated = dtCreated;
        this.dtUpdated = dtUpdated;
    }
    public UUID getId() {
        return uuid;
    }
    public void setId(UUID uuid) {
        this.uuid = uuid;
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
        return "Doctor{" +
                "uuid=" + uuid +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", surName='" + surName + '\'' +
                ", position='" + position + '\'' +
                ", department='" + department + '\'' +
                ", dtCreated=" + dtCreated +
                ", dtUpdated=" + dtUpdated +
                '}';
    }
}
