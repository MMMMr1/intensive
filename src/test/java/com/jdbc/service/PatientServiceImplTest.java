package com.jdbc.service;

import com.jdbc.dto.patient.PatientCreateDto;
import com.jdbc.dto.patient.PatientEditDto;
import com.jdbc.dto.patient.PatientReadDto;
import com.jdbc.service.api.PatientService;
import com.jdbc.service.fabrics.PatientServiceSingleton;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyVetoException;
import java.util.NoSuchElementException;
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
    }
    @Test
    void test_WithValid_Data_create() {
        assertEquals(testUuid, service.findPatientById(testUuid).get().getId());
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
        PatientCreateDto patientDeleted = new PatientCreateDto(
                "TestPatient",
                "TestPatient",
                "TestPatient",
                "TestPatient",
                "TestPatient",
                "TestPatient"
        );
        testUuidDeleted = service.create(patientDeleted);
        assertFalse(service.findPatientById(testUuidDeleted).isEmpty());
        service.delete(testUuidDeleted);
        Assertions.assertThrows(NoSuchElementException.class, () -> service.findPatientById(testUuidDeleted).get());
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
    @AfterEach
    public void down(){
        service.delete(testUuid);
    }
}