package com.jdbc.service;

import com.jdbc.dto.patient.PatientCreateDto;
import com.jdbc.dto.patient.PatientEditDto;
import com.jdbc.dto.patient.PatientReadDto;
import com.jdbc.service.api.PatientService;
import com.jdbc.service.fabrics.PatientServiceSingleton;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import java.beans.PropertyVetoException;
import java.util.UUID;
class PatientServiceImplTest {

     private PatientService service;

    {
        try {
            service = PatientServiceSingleton.getInstance();
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void test_WithValid_Data_create() {
        PatientCreateDto createPatient = new PatientCreateDto(
                "TestPatient",
                "TestPatient",
                "TestPatient",
                "TestPatient",
                "TestPatient",
                "TestPatient"
        );
            service.create(createPatient);
    }

    @Test
    void test_WithRightUUID_findPatientById() {
        UUID uuid =UUID.fromString("0171cb42-e21f-46db-aba7-c4b9107df99f");
        assertFalse( service.findPatientById(uuid).isEmpty());
    }
    @Test
    void test_WithWrongUUID_findPatientById() {
        UUID wrongUuid =UUID.fromString("0171cb42-e21f-46db-aba7-c4b9107df991");
        assertTrue(service.findPatientById(wrongUuid).isEmpty());
    }

    @Test
    void test_WithRightUUID_delete() {
        UUID uuid =UUID.fromString("74fd2462-990d-476f-bc39-09d49dfd59d7");
        assertFalse( service.findPatientById(uuid).isEmpty());
        service.delete(uuid);
        assertTrue( service.findPatientById(uuid).isEmpty());
    }

    @Test
    void test_WithRightUUID_update() {
        UUID uuid =UUID.fromString("84b5207a-66f7-4c66-8774-839eca632678");
        PatientReadDto patientById = service.findPatientById(uuid).get();
        PatientEditDto patient = new PatientEditDto(
                patientById.getId(),
                patientById.getLastName(),
                patientById.getSurName(),
                patientById.getSurName(),
                "Гомель",
                patientById.getPhone(),
                patientById.getMedicalCardNumber()
        );
        service.update(uuid,patient);
        assertEquals(patient.getAddress(), service.findPatientById(uuid).get().getAddress());
    }
    @Test
    void test_NotEmpty_findAll() {
        assertFalse(service.findAll().isEmpty());
    }
}