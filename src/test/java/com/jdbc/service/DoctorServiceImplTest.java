package com.jdbc.service;

import com.jdbc.dao.api.DoctorDao;
import com.jdbc.dto.doctor.DoctorCreateDto;
import com.jdbc.dto.doctor.DoctorEditDto;
import com.jdbc.dto.doctor.DoctorReadDto;
import com.jdbc.service.api.DoctorService;
import com.jdbc.service.fabrics.DoctorServiceSingleton;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyVetoException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DoctorServiceImplTest {

    private DoctorService service;
    private UUID testUuid;
    private UUID testUuidDeleted;

    {
        try {
            service = DoctorServiceSingleton.getInstance();
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
    }
    @BeforeEach
    void init() {
        DoctorCreateDto doctorCreateDto = new DoctorCreateDto(
                "TestPatient",
                "TestPatient",
                "TestPatient",
                "TestPatient",
                "TestPatient"
        );
        testUuid = service.create(doctorCreateDto);

        DoctorCreateDto doctorDeleted = new DoctorCreateDto(
                "TestPatient",
                "TestPatient",
                "TestPatient",
                "TestPatient",
                "TestPatient"
        );
        testUuidDeleted = service.create(doctorDeleted);
    }

    @Test
    void test_WithRightUUID_findDoctorById() {

        assertFalse( service.findDoctorById(testUuid).isEmpty());
    }
    @Test
    void test_WithWrongUUID_findPatientById() {
        UUID wrongUuid =UUID.fromString("0171cb42-e21f-46db-aba7-c4b9107df991");
        assertTrue(service.findDoctorById(wrongUuid).isEmpty());
    }

    @Test
    void test_WithRightUUID_delete() {
        assertFalse( service.findDoctorById(testUuidDeleted).isEmpty());
        service.delete(testUuidDeleted);
        assertTrue( service.findDoctorById(testUuidDeleted).isEmpty());
    }

    @Test
    void test_WithRightUUID_update() {
        DoctorReadDto doctorById = service.findDoctorById(testUuid).get();
        DoctorEditDto doctor = new DoctorEditDto(
                doctorById.getId(),
                doctorById.getLastName(),
                doctorById.getSurName(),
                doctorById.getSurName(),
                "отоларинголог",
                "терапевтическое"

        );
        service.update(testUuid,doctor);
        assertEquals(doctor.getPosition(), service.findDoctorById(testUuid).get().getPosition());
    }
    @Test
    void test_NotEmpty_findAll() {
        assertFalse(service.findAll().isEmpty());
    }

}