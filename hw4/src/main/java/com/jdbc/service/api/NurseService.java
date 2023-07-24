package com.jdbc.service.api;

import com.jdbc.dto.nurse.NurseCreateDto;
import com.jdbc.dto.nurse.NurseEditDto;
import com.jdbc.dto.nurse.NurseReadDto;
import com.jdbc.entity.Nurse;

import java.util.List;
import java.util.Optional;

public interface NurseService {
    Long create (NurseCreateDto nurse);
    Optional<Nurse> findNurseById(Long id);
    void delete(Long id);
    void update(Long id, NurseEditDto t);
    List<NurseReadDto> findAll();


}
