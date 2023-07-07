package com.jdbc.service;

import com.jdbc.dto.doctor.DoctorCreateDto;
import com.jdbc.dto.doctor.DoctorEditDto;
import com.jdbc.dto.doctor.DoctorReadDto;
import com.jdbc.service.api.DoctorService;
import com.jdbc.service.fabrics.DoctorServiceSingleton;
import org.junit.jupiter.api.Test;

import java.beans.PropertyVetoException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DoctorServiceImplTest {

    private DoctorService service;

    {
        try {
            service = DoctorServiceSingleton.getInstance();
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void test_WithValid_Data_create() {
        DoctorCreateDto doctorCreateDto = new DoctorCreateDto(
                "TestPatient",
                "TestPatient",
                "TestPatient",
                "TestPatient",
                "TestPatient"
        );
        service.create(doctorCreateDto);
    }

    @Test
    void test_WithRightUUID_findDoctorById() {
        UUID uuid =UUID.fromString("e25ceaef-5cf0-4312-b60c-37f85521c50a");
        assertFalse( service.findDoctorById(uuid).isEmpty());
    }
    @Test
    void test_WithWrongUUID_findPatientById() {
        UUID wrongUuid =UUID.fromString("0171cb42-e21f-46db-aba7-c4b9107df991");
        assertTrue(service.findDoctorById(wrongUuid).isEmpty());
    }

    @Test
    void test_WithRightUUID_delete() {
        UUID uuid =UUID.fromString("b0188c23-b6a9-4bcf-b21e-2fa5a1efcc8c");
        assertFalse( service.findDoctorById(uuid).isEmpty());
        service.delete(uuid);
        assertTrue( service.findDoctorById(uuid).isEmpty());
    }

    @Test
    void test_WithRightUUID_update() {
        UUID uuid =UUID.fromString("e25ceaef-5cf0-4312-b60c-37f85521c50a");
        DoctorReadDto doctorById = service.findDoctorById(uuid).get();
        DoctorEditDto doctor = new DoctorEditDto(
                doctorById.getId(),
                doctorById.getLastName(),
                doctorById.getSurName(),
                doctorById.getSurName(),
                "отоларинголог",
                "терапевтическое"

        );
        service.update(uuid,doctor);
        assertEquals(doctor.getPosition(), service.findDoctorById(uuid).get().getPosition());
    }
    @Test
    void test_NotEmpty_findAll() {
        assertFalse(service.findAll().isEmpty());
    }
}