package com.hospital.configuration;

import com.hospital.core.converter.doctor.DoctorDtoToDoctor;
import com.hospital.core.converter.doctor.DoctorToDoctorDto;
import com.hospital.core.converter.medical_history.MedicalHistoryDtoToMedicalHistory;
import com.hospital.core.converter.medical_history.MedicalHistoryToMedicalHistoryDto;
import com.hospital.core.converter.nurse.NurseDtoToNurse;
import com.hospital.core.converter.nurse.NurseToNurseDto;
import com.hospital.core.converter.patient.PatientDtoToPatient;
import com.hospital.core.converter.patient.PatientToPatientDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class ConversionConfiguration {
    @Bean
    ConversionServiceFactoryBean conversionService() {
        ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
        Set<Converter> converters = new HashSet<>();
        converters.add(new DoctorDtoToDoctor());
        converters.add(new DoctorToDoctorDto());
        converters.add(new MedicalHistoryDtoToMedicalHistory());
        converters.add(new MedicalHistoryToMedicalHistoryDto());
        converters.add(new NurseDtoToNurse());
        converters.add(new NurseToNurseDto());
        converters.add(new PatientDtoToPatient());
        converters.add(new PatientToPatientDto());
        bean.setConverters(converters);
        return bean;
    }
}
