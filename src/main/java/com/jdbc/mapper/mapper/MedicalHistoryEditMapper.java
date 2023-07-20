package com.jdbc.mapper.mapper;

import com.jdbc.dto.medical_history.MedicalHistoryEditDto;
import com.jdbc.entity.MedicalHistory;
import com.jdbc.mapper.Mapper;


public class MedicalHistoryEditMapper implements Mapper<MedicalHistoryEditDto, MedicalHistory> {

    @Override
    public MedicalHistory map(MedicalHistoryEditDto object) {
        MedicalHistory history = new MedicalHistory();
        copy(object, history);
        return history;
    }

    private void copy(MedicalHistoryEditDto object, MedicalHistory history) {
        history.setUuid(object.getUuid());
        history.setDiagnosis(object.getDiagnosis());
        history.setTreatment(object.getTreatment());
    }

}
