package com.hospital.core.converter.doctor;

import com.hospital.core.dto.DoctorDto;
import com.hospital.entity.Doctor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DoctorToDoctorDto implements  Converter<Doctor, DoctorDto> {
    @Override
    public DoctorDto convert(Doctor object) {
        return new DoctorDto(
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
