package com.jdbc.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "app.medical_histories")
public class MedicalHistory  {
    @Id
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_uuid")
    private Patient patient;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;
    @Column
    private String diagnosis;
    @Column
    private String treatment;
    @Column(name = "dt_created")
    private LocalDateTime dtCreated;
    @Column(name = "dt_updated")
    private LocalDateTime dtUpdated;

    public MedicalHistory() {
    }

    public MedicalHistory(Long id, Patient patient, Doctor doctor, String diagnosis, String treatment, LocalDateTime dtCreated, LocalDateTime dtUpdated) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.dtCreated = dtCreated;
        this.dtUpdated = dtUpdated;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public LocalDateTime getDtCreated() {
        return dtCreated;
    }

    public LocalDateTime getDtUpdated() {
        return dtUpdated;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public void setDtCreated(LocalDateTime dtCreated) {
        this.dtCreated = dtCreated;
    }

    public void setDtUpdated(LocalDateTime dtUpdated) {
        this.dtUpdated = dtUpdated;
    }
}
