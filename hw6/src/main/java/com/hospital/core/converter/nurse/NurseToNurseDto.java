package com.hospital.core.converter.nurse;

import com.hospital.core.dto.DoctorDto;
import com.hospital.core.dto.NurseDto;
import com.hospital.entity.Doctor;
import com.hospital.entity.Nurse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NurseToNurseDto implements  Converter<Nurse, NurseDto> {
    @Override
    public NurseDto convert(Nurse object) {
        return new NurseDto(
                object.getId(),
                object.getLastName(),
                object.getFirstName(),
                object.getSurName(),
                object.getPosition(),
                object.getDepartment(),
                object.getBlockfloor(),
                object.getBlockcode(),
                object.getDtCreated(),
                object.getDtUpdated());
    }
}
