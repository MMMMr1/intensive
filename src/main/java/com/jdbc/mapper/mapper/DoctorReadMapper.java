package com.jdbc.mapper.mapper;

import com.jdbc.dto.doctor.DoctorReadDto;
import com.jdbc.entity.Doctor;
import com.jdbc.mapper.Mapper;


public class DoctorReadMapper implements Mapper<Doctor, DoctorReadDto> {

    @Override
    public DoctorReadDto map(Doctor object) {
         return new DoctorReadDto(
                object.getId(),
                 object.getLastName(),
                 object.getFirstName(),
                 object.getSurName(),
                 object.getPosition(),
                 object.getDepartment(),
                 object.getDtCreated(),
                 object.getDtUpdated()
        );
    }
}
