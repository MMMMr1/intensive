package com.jdbc.mapper.mapper;

import com.jdbc.dto.patient.PatientReadDto;
import com.jdbc.entity.Patient;
import com.jdbc.mapper.Mapper;


public class PatientReadMapper implements Mapper<Patient, PatientReadDto> {

    @Override
    public PatientReadDto map(Patient object) {
         return new PatientReadDto(
                object.getId(),
                 object.getLastName(),
                 object.getFirstName(),
                 object.getSurName(),
                 object.getAddress(),
                 object.getPhone(),
                 object.getMedicalCardNumber(),
                 object.getDtCreated(),
                 object.getDtUpdated()
        );
    }
}
