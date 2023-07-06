package com.jdbc.mapper.mapper;

import com.jdbc.dto.PatientEditDto;
import com.jdbc.entity.Patient;
import com.jdbc.mapper.Mapper;


public class PatientEditMapper implements Mapper<PatientEditDto, Patient> {

    @Override
    public Patient map(PatientEditDto object) {
        Patient patient = new Patient();
        copy(object, patient);
        return patient;
    }

    private void copy(PatientEditDto object, Patient patient) {
        patient.setId(object.getUuid());
        patient.setFirstName(object.getFirstName());
        patient.setAddress(object.getAddress());
        patient.setLastName(object.getLastName());
        patient.setMedicalCardNumber(object.getMedicalCardNumber());
        patient.setSurName(object.getSurName());
        patient.setPhone(object.getPhone());
    }

}
