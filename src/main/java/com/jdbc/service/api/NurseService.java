package com.jdbc.service.api;



import com.jdbc.dto.doctor.DoctorCreateDto;
import com.jdbc.dto.doctor.DoctorEditDto;
import com.jdbc.dto.doctor.DoctorReadDto;
import com.jdbc.dto.nurse.NurseCreateDto;
import com.jdbc.entity.Nurse;

import java.util.List;
import java.util.Optional;

public interface NurseService {
    Long create (NurseCreateDto nurse);
    Optional<Nurse> findNurseById(Long id);



}
