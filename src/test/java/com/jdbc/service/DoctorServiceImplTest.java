package com.jdbc.service;

import com.jdbc.dto.doctor.DoctorCreateDto;
import com.jdbc.dto.doctor.DoctorEditDto;
import com.jdbc.dto.doctor.DoctorReadDto;
import com.jdbc.service.api.DoctorService;
import com.jdbc.service.fabrics.DoctorServiceSingleton;
import org.junit.jupiter.api.*;

import java.beans.PropertyVetoException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DoctorServiceImplTest {
        private DoctorService service;
        private Long testUuid;
        private Long testUuidDeleted;
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
        }
    @Test
    void test_WithRightUUID_findDoctorById() {
        assertFalse( service.findDoctorById(testUuid).isEmpty());
    }
    @Test
    void test_WithWrongUUID_findPatientById() {
//        UUID wrongUuid =Long.fromString("0171cb42-e21f-46db-aba7-c4b9107df991");
        assertTrue(service.findDoctorById(222222222L).isEmpty());
    }
    @Test
    void test_WithRightUUID_delete() {
        DoctorCreateDto doctorDeleted = new DoctorCreateDto(
                "TestPatient",
                "TestPatient",
                "TestPatient",
                "TestPatient",
                "TestPatient"
        );
        testUuidDeleted = service.create(doctorDeleted);
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
    @AfterEach
    public void down(){
        service.delete(testUuid);
    }
}