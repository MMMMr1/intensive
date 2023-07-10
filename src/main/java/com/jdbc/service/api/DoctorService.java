package com.jdbc.service.api;



import com.jdbc.dto.doctor.DoctorCreateDto;
import com.jdbc.dto.doctor.DoctorEditDto;
import com.jdbc.dto.doctor.DoctorReadDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DoctorService {
    Long create ( DoctorCreateDto patient );
    Optional<DoctorReadDto> findDoctorById(Long id);
    void delete(Long id);
    void update(Long id, DoctorEditDto t);
    List<DoctorReadDto> findAll();



}
