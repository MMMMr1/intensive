package com.jdbc.mapper.mapper;

import com.jdbc.dto.patient.PatientReadDto;
import com.jdbc.entity.Patient;
import com.jdbc.mapper.Mapper;


public class PatientEntityMapper implements Mapper<PatientReadDto, Patient> {

    @Override
    public Patient map(PatientReadDto object) {
         return new Patient(
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
