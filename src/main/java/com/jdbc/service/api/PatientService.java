package com.jdbc.service.api;


import com.jdbc.dto.PatientCreateDto;
import com.jdbc.dto.PatientEditDto;
import com.jdbc.dto.PatientReadDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PatientService  {
    void create ( PatientCreateDto patient );
    Optional<PatientReadDto> findPatientById(UUID id );
    void delete(UUID uuid);
    void update(UUID uuid, PatientEditDto t);
    List<PatientReadDto> findAll();



}
