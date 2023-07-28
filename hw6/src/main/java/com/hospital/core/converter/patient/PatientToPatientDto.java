package com.hospital.core.converter.patient;

import com.hospital.core.dto.PatientDto;
import com.hospital.entity.Patient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PatientToPatientDto implements Converter<Patient, PatientDto> {
    @Override
    public PatientDto convert(Patient object) {
         return new PatientDto(
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
