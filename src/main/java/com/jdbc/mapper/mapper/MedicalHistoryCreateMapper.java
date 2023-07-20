package com.jdbc.mapper.mapper;

import com.jdbc.dto.medical_history.MedicalHistoryCreateDto;
import com.jdbc.entity.MedicalHistory;
import com.jdbc.mapper.Mapper;

public class MedicalHistoryCreateMapper implements Mapper<MedicalHistoryCreateDto, MedicalHistory> {
    @Override
    public MedicalHistory map(MedicalHistoryCreateDto object) {
        MedicalHistory history = new MedicalHistory();
        copy(object, history);
        return history;
    }

    private void copy(MedicalHistoryCreateDto object, MedicalHistory history) {
        history.setDiagnosis(object.getDiagnosis());
        history.setTreatment(object.getTreatment());
    }
}
