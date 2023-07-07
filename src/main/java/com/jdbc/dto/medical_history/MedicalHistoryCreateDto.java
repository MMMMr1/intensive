package com.jdbc.dto.medical_history;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public class MedicalHistoryCreateDto {
    @JsonProperty("patient")
    private UUID patient;
    @JsonProperty("doctor")
    private UUID doctor;
    @JsonProperty("diagnosis")
    private String diagnosis;
    @JsonProperty("treatment")
    private String treatment;

    public MedicalHistoryCreateDto() {
    }

    public MedicalHistoryCreateDto(UUID patient, UUID doctor, String diagnosis, String treatment) {
        this.patient = patient;
        this.doctor = doctor;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
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

    @Override
    public String toString() {
        return "MedicalHistoryCreateDto{" +
                "patient=" + patient +
                ", doctor=" + doctor +
                ", diagnosis='" + diagnosis + '\'' +
                ", treatment='" + treatment + '\'' +
                '}';
    }
}
