package com.jdbc.service;

import com.jdbc.dto.doctor.DoctorCreateDto;
import com.jdbc.dto.patient.PatientCreateDto;
import com.jdbc.dto.patient.PatientEditDto;
import com.jdbc.dto.patient.PatientReadDto;
import com.jdbc.service.api.PatientService;
import com.jdbc.service.fabrics.PatientServiceSingleton;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyVetoException;
import java.util.UUID;

class PatientServiceImplTest {

    private PatientService service;
    private UUID testUuid;
    private UUID testUuidDeleted;

    {
        try {
            service = PatientServiceSingleton.getInstance();
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
    }
    @BeforeEach
    void init() {
        PatientCreateDto createPatient = new PatientCreateDto(
                "TestPatient",
                "TestPatient",
                "TestPatient",
                "TestPatient",
                "TestPatient",
                "TestPatient"
        );
        testUuid = service.create(createPatient);

        PatientCreateDto patientDeleted = new PatientCreateDto(
                "TestPatient",
                "TestPatient",
                "TestPatient",
                "TestPatient",
                "TestPatient",
                "TestPatient"
        );
        testUuidDeleted = service.create(patientDeleted);
    }

    @Test
    void test_WithValid_Data_create() {

    }

    @Test
    void test_WithRightUUID_findPatientById() {
        assertFalse(service.findPatientById(testUuid).isEmpty());
    }

    @Test
    void test_WithWrongUUID_findPatientById() {
        UUID wrongUuid = UUID.fromString("0171cb42-e21f-46db-aba7-c4b9107df991");
        assertTrue(service.findPatientById(wrongUuid).isEmpty());
    }

    @Test
    void test_WithRightUUID_delete() {
        assertFalse(service.findPatientById(testUuidDeleted).isEmpty());
        service.delete(testUuidDeleted);
        assertTrue(service.findPatientById(testUuidDeleted).isEmpty());
    }

    @Test
    void test_WithRightUUID_update() {
        PatientReadDto patientById = service.findPatientById(testUuid).get();
        PatientEditDto patient = new PatientEditDto(
                patientById.getId(),
                patientById.getLastName(),
                patientById.getSurName(),
                patientById.getSurName(),
                "Гомель",
                patientById.getPhone(),
                patientById.getMedicalCardNumber()
        );
        service.update(testUuid, patient);
        assertEquals(patient.getAddress(), service.findPatientById(testUuid).get().getAddress());
    }

    @Test
    void test_NotEmpty_findAll() {
        assertFalse(service.findAll().isEmpty());
    }
}