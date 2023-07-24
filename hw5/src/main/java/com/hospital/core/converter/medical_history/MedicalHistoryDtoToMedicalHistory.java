package com.hospital.core.converter.medical_history;

import com.hospital.core.dto.MedicalHistoryDto;
import com.hospital.entity.MedicalHistory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MedicalHistoryDtoToMedicalHistory implements Converter<MedicalHistoryDto, MedicalHistory> {
    @Override
    public MedicalHistory convert(MedicalHistoryDto object) {
        MedicalHistory history = new MedicalHistory();
        copy(object, history);
        return history;
    }

    private void copy(MedicalHistoryDto object, MedicalHistory history) {
        history.setDiagnosis(object.getDiagnosis());
        history.setTreatment(object.getTreatment());
    }
}
