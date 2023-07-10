package com.jdbc.service;

import com.jdbc.dto.doctor.DoctorCreateDto;
import com.jdbc.dto.medical_history.MedicalHistoryCreateDto;
import com.jdbc.dto.medical_history.MedicalHistoryEditDto;
import com.jdbc.dto.medical_history.MedicalHistoryReadDto;
import com.jdbc.dto.patient.PatientCreateDto;
import com.jdbc.service.api.DoctorService;
import com.jdbc.service.api.MedicalHistoryService;
import com.jdbc.service.api.PatientService;
import com.jdbc.service.fabrics.DoctorServiceSingleton;
import com.jdbc.service.fabrics.MedicalHistoryServiceSingleton;
import com.jdbc.service.fabrics.PatientServiceSingleton;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.beans.PropertyVetoException;
import java.util.UUID;

import static org.junit.Assert.*;

class MedicalHistoryServiceImplTest {

    private MedicalHistoryService service;
    private DoctorService doctorService;
    private PatientService patientService;
    private UUID testUuid;
    private UUID testUuidDeleted;
    private UUID patient;
    private Long doctor;

    {
        try {
            service = MedicalHistoryServiceSingleton.getInstance();
            doctorService = DoctorServiceSingleton.getInstance();
            patientService = PatientServiceSingleton.getInstance();
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
        doctor = doctorService.create(doctorCreateDto);
        PatientCreateDto createPatient = new PatientCreateDto(
                "TestPatient",
                "TestPatient",
                "TestPatient",
                "TestPatient",
                "TestPatient",
                "TestPatient"
        );
        patient = patientService.create(createPatient);
        MedicalHistoryCreateDto createHistory = new MedicalHistoryCreateDto(
                patient,
                doctor,
                "GG2 - dkd",
                "dddddd");
        testUuid = service.create(createHistory);
    }


    @Test
    void test_WithRightUUID_findMedicalHistoryById() {
        assertFalse( service.findMedicalHistoryById(testUuid).isEmpty());
    }
    @Test
    void test_WithWrongUUID_findMedicalHistoryById() {
        UUID wrongUuid =UUID.fromString("0171cb42-e21f-46db-aba7-c4b9107df991");
        assertTrue(service.findMedicalHistoryById(wrongUuid).isEmpty());
    }

    @Test
    void test_WithRightUUID_update() {
        MedicalHistoryReadDto historyReadDto = service.findMedicalHistoryById(testUuid).get();
        MedicalHistoryEditDto history= new MedicalHistoryEditDto(
                testUuid,
                historyReadDto.getPatient(),
                historyReadDto.getDoctor(),
                "Update - value",
                "Update - value");
        service.update(testUuid,history);
        assertEquals(history.getDiagnosis(), service.findMedicalHistoryById(testUuid).get().getDiagnosis());
        assertEquals(history.getTreatment(), service.findMedicalHistoryById(testUuid).get().getTreatment());
    }

    @Test
    void test_NotEmpty_findAll() {
        assertFalse(service.findAll().isEmpty());
    }
    @Test
    void test_WithRightUUID_delete() {
        MedicalHistoryCreateDto historyDeleted = new MedicalHistoryCreateDto(
                patient,
                doctor,
                "DDD - dkd",
                "dddddd");
        testUuidDeleted = service.create(historyDeleted);
        assertFalse( service.findMedicalHistoryById(testUuidDeleted).isEmpty());
        service.delete(testUuidDeleted);
        assertTrue( service.findMedicalHistoryById(testUuidDeleted).isEmpty());
    }
    @AfterEach
    public void down(){
        service.delete(testUuid);
        patientService.delete(patient);
        doctorService.delete(doctor);
    }
}