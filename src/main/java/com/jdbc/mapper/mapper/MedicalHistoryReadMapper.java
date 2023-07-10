package com.jdbc.mapper.mapper;

import com.jdbc.dto.medical_history.MedicalHistoryReadDto;
import com.jdbc.entity.MedicalHistory;
import com.jdbc.mapper.Mapper;


public class MedicalHistoryReadMapper implements Mapper<MedicalHistory, MedicalHistoryReadDto> {

    @Override
    public MedicalHistoryReadDto map(MedicalHistory object) {
         return new MedicalHistoryReadDto(
                  object.getUuid(),
                 object.getPatient(),
                 object.getDoctor(),
                 object.getDiagnosis(),
                 object.getTreatment(),
                 object.getDtCreated(),
                 object.getDtUpdated()
                 );
    }
}
