package com.jdbc.mapper.mapper;

import com.jdbc.dto.doctor.DoctorReadDto;
import com.jdbc.entity.Doctor;
import com.jdbc.mapper.Mapper;


public class DoctorEntityMapper implements Mapper<DoctorReadDto, Doctor> {

    @Override
    public Doctor  map(DoctorReadDto object) {
         return new Doctor(
                object.getId(),
                 object.getLastName(),
                 object.getFirstName(),
                 object.getSurName(),
                 object.getPosition(),
                 object.getDepartment(),
                 object.getWorkHours(),
                 object.getDtCreated(),
                 object.getDtUpdated()
        );
    }
}
