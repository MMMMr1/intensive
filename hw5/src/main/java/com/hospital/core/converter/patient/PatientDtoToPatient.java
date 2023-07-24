package com.hospital.core.converter.patient;

import com.hospital.core.dto.PatientDto;
import com.hospital.entity.Patient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PatientDtoToPatient implements Converter<PatientDto, Patient> {
    @Override
    public Patient convert(PatientDto object) {
        Patient patient = new Patient();
        copy(object, patient);
        return patient;
    }

    private void copy(PatientDto object, Patient patient) {
        patient.setFirstName(object.getFirstName());
        patient.setAddress(object.getAddress());
        patient.setLastName(object.getLastName());
        patient.setMedicalCardNumber(object.getMedicalCardNumber());
        patient.setSurName(object.getSurName());
        patient.setPhone(object.getPhone());
    }

}
