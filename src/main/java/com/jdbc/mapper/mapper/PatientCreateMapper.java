package com.jdbc.mapper.mapper;

import com.jdbc.dto.PatientCreateDto;
import com.jdbc.entity.Patient;
import com.jdbc.mapper.Mapper;

public class PatientCreateMapper implements Mapper<PatientCreateDto, Patient> {
    @Override
    public Patient map(PatientCreateDto object) {
        Patient patient = new Patient();
        copy(object, patient);
        return patient;
    }

    private void copy(PatientCreateDto object, Patient patient) {
        patient.setFirstName(object.getFirstName());
        patient.setAddress(object.getAddress());
        patient.setLastName(object.getLastName());
        patient.setMedicalCardNumber(object.getMedicalCardNumber());
        patient.setSurName(object.getSurName());
        patient.setPhone(object.getPhone());
    }

}
