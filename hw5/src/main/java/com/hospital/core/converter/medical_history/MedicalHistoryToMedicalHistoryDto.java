package com.hospital.core.converter.medical_history;

import com.hospital.core.dto.MedicalHistoryDto;
import com.hospital.entity.MedicalHistory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MedicalHistoryToMedicalHistoryDto implements  Converter<MedicalHistory, MedicalHistoryDto> {
    @Override
    public MedicalHistoryDto convert(MedicalHistory object) {
        return new MedicalHistoryDto(
                object.getId(),
                object.getPatient().getId(),
                object.getDoctor().getId(),
                object.getDiagnosis(),
                object.getTreatment(),
                object.getDtCreated(),
                object.getDtUpdated()
        );
    }
}
