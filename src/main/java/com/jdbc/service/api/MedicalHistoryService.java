package com.jdbc.service.api;
import com.jdbc.dto.medical_history.MedicalHistoryCreateDto;
import com.jdbc.dto.medical_history.MedicalHistoryEditDto;
import com.jdbc.dto.medical_history.MedicalHistoryReadDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MedicalHistoryService {
    void create ( MedicalHistoryCreateDto patient );
    Optional<MedicalHistoryReadDto> findMedicalHistoryById(UUID id );
    void delete(UUID uuid);
    void update(UUID uuid, MedicalHistoryEditDto t);
    List<MedicalHistoryReadDto> findAll();



}
