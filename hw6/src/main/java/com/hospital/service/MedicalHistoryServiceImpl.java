package com.hospital.service;

import com.hospital.core.dto.MedicalHistoryDto;
import com.hospital.core.exception.InvalidVersionException;
import com.hospital.core.exception.employee.EmployeeNotFoundException;
import com.hospital.core.exception.medical_history.MedicalHistoryNotFoundException;
import com.hospital.entity.Doctor;
import com.hospital.entity.MedicalHistory;
import com.hospital.entity.Patient;
import com.hospital.repository.api.MedicalHistoryRepository;
import com.hospital.service.api.DoctorService;
import com.hospital.service.api.MedicalHistoryService;
import com.hospital.service.api.PatientService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MedicalHistoryServiceImpl implements MedicalHistoryService {
    private final MedicalHistoryRepository repository;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final ConversionService conversionService;

    @Transactional
    @Override
    public MedicalHistoryDto create(MedicalHistoryDto medicalHistoryDto) {
        if (!conversionService.canConvert(MedicalHistoryDto.class, MedicalHistory.class)) {
            throw new IllegalStateException("Can not convert MedicalHistoryDto.class to MedicalHistory.class");
        }

        return Optional.of(medicalHistoryDto)
                .map(s -> {
                    MedicalHistory  history = conversionService.convert(medicalHistoryDto, MedicalHistory.class);
                    Doctor doctor = doctorService.findById(medicalHistoryDto.getDoctor());
                    Patient patient = patientService.findById(medicalHistoryDto.getPatient());
                    history.setDoctor(doctor);
                    history.setPatient(patient);
                    Instant dtCreated = Instant.now();
                    history.setDtCreated(dtCreated);
                    history.setDtUpdated(dtCreated);
                    return history;
                })
                .map(repository::save)
                .map(entity -> conversionService.convert(entity, MedicalHistoryDto.class))
                .get();
    }

    @Override
    public MedicalHistory findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new MedicalHistoryNotFoundException("Medical history with id "+ id + "not found"));

    }

    @Override
    public MedicalHistoryDto findMedicalHistoryById(Long id) {
        return repository.findById(id)
                .map(s -> conversionService.convert(s, MedicalHistoryDto.class))
                .orElseThrow(() -> new MedicalHistoryNotFoundException("Medical history with id "+ id + "not found"));

    }

    @Transactional
    @Override
    public Optional<MedicalHistoryDto> update(Long id, Instant version, MedicalHistoryDto medicalHistoryDto) {
        return repository.findById(id)
                .map(doctorEntity -> {
                    if (version.toEpochMilli() != doctorEntity.getDtUpdated().toEpochMilli()) {
                        throw new InvalidVersionException("Invalid version");
                    }
                    Doctor doctor = doctorService.findById(medicalHistoryDto.getDoctor()) ;
                    Patient patient = patientService.findById(medicalHistoryDto.getPatient());
                    doctorEntity.setDtUpdated(Instant.now());
                    doctorEntity.setPatient(patient);
                    doctorEntity.setDoctor(doctor);
                    doctorEntity.setDiagnosis(medicalHistoryDto.getDiagnosis());
                    doctorEntity.setTreatment(medicalHistoryDto.getTreatment());
                    return doctorEntity;
                })
                .map(repository::saveAndFlush)
                .map(s -> conversionService.convert(s, MedicalHistoryDto.class));
    }

    @Override
    public Page<MedicalHistoryDto> getPage(Pageable paging) {
        if (!conversionService.canConvert(MedicalHistory.class, MedicalHistoryDto.class)) {
            throw new IllegalStateException("Can not convert DoctorDto.class to Doctor.class");
        }
        return repository.findAll(paging)
                .map(s -> conversionService.convert(s, MedicalHistoryDto.class));
    }
    @Transactional
    @Override
    public boolean delete(Long id) {
        return repository.findById(id)
                .map(entity -> {
                    repository.delete(entity);
                    repository.flush();
                    return true;
                })
                .orElse(false);
    }


//
//    @Override
//    public Page<DoctorDto> getPage(Pageable paging) {
//        if (!conversionService.canConvert(com.hospital.entity.Doctor.class, DoctorDto.class)) {
//            throw new IllegalStateException("Can not convert DoctorDto.class to Doctor.class");
//        }
//        return  repository.findAll(paging)
//                .map(s -> conversionService.convert(s, DoctorDto.class));
//    }
//
//
//    private void copy(DoctorDto object, com.hospital.entity.Doctor doctor) {
//        doctor.setFirstName(object.getFirstName());
//        doctor.setLastName(object.getLastName());
//        doctor.setSurName(object.getSurName());
//        doctor.setPosition(object.getPosition());
//        doctor.setDepartment(object.getDepartment());
//        doctor.setWorkHours(object.getWorkHours());
//    }



}
