package com.jdbc.service.api;



import com.jdbc.dto.doctor.DoctorCreateDto;
import com.jdbc.dto.doctor.DoctorEditDto;
import com.jdbc.dto.doctor.DoctorReadDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DoctorService {
    UUID create ( DoctorCreateDto patient );
    Optional<DoctorReadDto> findDoctorById(UUID id );
    void delete(UUID uuid);
    void update(UUID uuid, DoctorEditDto t);
    List<DoctorReadDto> findAll();



}
