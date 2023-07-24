package com.jdbc.mapper.mapper;

import com.jdbc.dto.doctor.DoctorEditDto;
import com.jdbc.entity.Doctor;
import com.jdbc.mapper.Mapper;


public class DoctorEditMapper implements Mapper<DoctorEditDto, Doctor> {

    @Override
    public Doctor map(DoctorEditDto object) {
        Doctor doctor = new Doctor();
        copy(object, doctor);
        return doctor;
    }

    private void copy(DoctorEditDto object, Doctor doctor) {
        doctor.setId(object.getUuid());
        doctor.setFirstName(object.getFirstName());
        doctor.setPosition(object.getPosition());
        doctor.setLastName(object.getLastName());
        doctor.setDepartment(object.getDepartment());
        doctor.setSurName(object.getSurName());
    }

}
