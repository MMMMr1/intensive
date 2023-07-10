package com.jdbc.entity;


import java.time.LocalDateTime;
import java.util.UUID;


public class MedicalHistory implements Entity {
    private UUID uuid;
    private UUID patient;
    private UUID doctor;
    private String diagnosis;
    private String treatment;
    private LocalDateTime dtCreated;
    private LocalDateTime dtUpdated;

    public MedicalHistory() {
    }

    public MedicalHistory(UUID uuid, UUID patient, UUID doctor, String diagnosis, String treatment, LocalDateTime dtCreated, LocalDateTime dtUpdated) {
        this.uuid = uuid;
        this.patient = patient;
        this.doctor = doctor;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.dtCreated = dtCreated;
        this.dtUpdated = dtUpdated;
    }

    public UUID getPatient() {
        return patient;
    }

    public void setPatient(UUID patient) {
        this.patient = patient;
    }

    public UUID getDoctor() {
        return doctor;
    }

    public void setDoctor(UUID doctor) {
        this.doctor = doctor;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
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

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }


}
