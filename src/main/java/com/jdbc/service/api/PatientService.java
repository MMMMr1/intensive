package com.jdbc.service.api;


import com.jdbc.dto.patient.PatientCreateDto;
import com.jdbc.dto.patient.PatientEditDto;
import com.jdbc.dto.patient.PatientReadDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PatientService  {
    UUID create ( PatientCreateDto patient );
    Optional<PatientReadDto> findPatientById(UUID id );
    void delete(UUID uuid);
    void update(UUID uuid, PatientEditDto t);
    List<PatientReadDto> findAll();



}
