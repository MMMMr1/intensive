package com.jdbc.mapper.mapper;

import com.jdbc.dto.doctor.DoctorCreateDto;
import com.jdbc.entity.Doctor;
import com.jdbc.mapper.Mapper;

public class DoctorCreateMapper implements Mapper<DoctorCreateDto, Doctor> {
    @Override
    public Doctor map(DoctorCreateDto object) {
        Doctor doctort = new Doctor();
        copy(object, doctort);
        return doctort;
    }

    private void copy(DoctorCreateDto object, Doctor doctor) {
        doctor.setFirstName(object.getFirstName());
        doctor.setPosition(object.getPosition());
        doctor.setLastName(object.getLastName());
        doctor.setDepartment(object.getDepartment());
        doctor.setSurName(object.getSurName());
    }

}
