package com.jdbc.service;

import com.jdbc.dto.medical_history.MedicalHistoryCreateDto;
import com.jdbc.dto.medical_history.MedicalHistoryEditDto;
import com.jdbc.dto.medical_history.MedicalHistoryReadDto;
import com.jdbc.service.api.MedicalHistoryService;
import com.jdbc.service.fabrics.MedicalHistoryServiceSingleton;
import org.junit.jupiter.api.Test;

import java.beans.PropertyVetoException;
import java.util.UUID;

import static org.junit.Assert.*;

class MedicalHistoryServiceImplTest {

     private MedicalHistoryService service;

    {
        try {
            service = MedicalHistoryServiceSingleton.getInstance();
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void test_WithValid_Data_create() {
        MedicalHistoryCreateDto createHistory = new MedicalHistoryCreateDto(
                UUID.fromString("0171cb42-e21f-46db-aba7-c4b9107df99f"),
                UUID.fromString("b5e747d8-3c8d-45bc-921d-e202df9bfa7f"),
                "GG2 - dkd",
                "dddddd");
            service.create(createHistory);
    }

    @Test
    void test_WithRightUUID_findMedicalHistoryById() {
        UUID uuid =UUID.fromString("514e50cc-9c2b-4d6b-b0f7-dc22a835131e");
        assertFalse( service.findMedicalHistoryById(uuid).isEmpty());
    }
    @Test
    void test_WithWrongUUID_findMedicalHistoryById() {
        UUID wrongUuid =UUID.fromString("0171cb42-e21f-46db-aba7-c4b9107df991");
        assertTrue(service.findMedicalHistoryById(wrongUuid).isEmpty());
    }

    @Test
    void test_WithRightUUID_delete() {
        UUID uuid =UUID.fromString("63750fa4-5ccd-4996-9e0f-2915a1164b7e");
        assertFalse( service.findMedicalHistoryById(uuid).isEmpty());
        service.delete(uuid);
        assertTrue( service.findMedicalHistoryById(uuid).isEmpty());
    }

    @Test
    void test_WithRightUUID_update() {
        UUID uuid =UUID.fromString("514e50cc-9c2b-4d6b-b0f7-dc22a835131e");
        MedicalHistoryReadDto historyReadDto = service.findMedicalHistoryById(uuid).get();
        MedicalHistoryEditDto history= new MedicalHistoryEditDto(
                uuid,
                historyReadDto.getPatient(),
                historyReadDto.getDoctor(),
                "Update - value",
                "Update - value");
        service.update(uuid,history);
        assertEquals(history.getDiagnosis(), service.findMedicalHistoryById(uuid).get().getDiagnosis());
        assertEquals(history.getTreatment(), service.findMedicalHistoryById(uuid).get().getTreatment());
    }
    @Test
    void test_NotEmpty_findAll() {
        assertFalse(service.findAll().isEmpty());
    }
}