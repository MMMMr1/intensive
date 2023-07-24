package com.hospital.core.converter.doctor;

import com.hospital.core.dto.DoctorDto;
import com.hospital.entity.Doctor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DoctorDtoToDoctor implements Converter<DoctorDto, Doctor> {
    @Override
    public Doctor convert(DoctorDto object) {
        Doctor doctor = new Doctor();
        doctor.setFirstName(object.getFirstName());
        doctor.setPosition(object.getPosition());
        doctor.setLastName(object.getLastName());
        doctor.setDepartment(object.getDepartment());
        doctor.setSurName(object.getSurName());
        return doctor;
    }
}
