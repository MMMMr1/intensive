package com.hospital.service;

import com.hospital.core.dto.PatientDto;
import com.hospital.core.exception.InvalidVersionException;
import com.hospital.core.exception.patient.PatientNotFoundException;
import com.hospital.entity.Patient;
import com.hospital.repository.api.PatientRepository;
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
public class PatientServiceImpl implements PatientService {
    private final PatientRepository repository;
    private final ConversionService conversionService;
    private static final Logger logger =
            LoggerFactory.getLogger(PatientServiceImpl.class);


    @Transactional
    @Override
    public PatientDto create(PatientDto patientDto) {
        if (!conversionService.canConvert(PatientDto.class, Patient.class)) {
            throw new IllegalStateException("Can not convert PatientDto.class to Patient.class");
        }
        return Optional.of(patientDto)
                .map(s -> {
                    Patient patient = conversionService.convert(patientDto, Patient.class);
                    Instant dtCreated = Instant.now();
                    patient.setDtCreated(dtCreated);
                    patient.setDtUpdated(dtCreated);
                    return patient;
                })
                .map(repository::save)
                .map(entity -> conversionService.convert(entity, PatientDto.class))
                .orElseThrow();
    }

    @Transactional
    @Override
    public Optional<PatientDto> update(Long id, Instant version, PatientDto patientDto) {
        return repository.findById(id)
                .map(entity -> {
                    if (version.toEpochMilli() != entity.getDtUpdated().toEpochMilli()) {
                        logger.error("Can not update patient " + id + "invalid version " + version);
                        throw new InvalidVersionException("Invalid version");
                    }
                    copy(patientDto, entity);
                    entity.setDtUpdated(Instant.now());
                    return entity;
                })
                .map(repository::saveAndFlush)
                .map(s -> conversionService.convert(s, PatientDto.class));
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

    @Override
    public Patient findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new PatientNotFoundException("There is no patient with such id"));
    }

    @Override
    public PatientDto findPatentById(Long id) {
        if (!conversionService.canConvert(Patient.class, PatientDto.class)) {
            throw new IllegalStateException("Can not convert PatientDto.class to Patient.class");
        }
        return repository.findById(id)
                .map(s -> conversionService.convert(s, PatientDto.class))
                .orElseThrow(() -> new PatientNotFoundException("There is no patient with such id"));
    }

    @Override
    public Page<PatientDto> getPage(Pageable paging) {
        if (!conversionService.canConvert(Patient.class, PatientDto.class)) {
            throw new IllegalStateException("Can not convert PatientDto.class to Patient.class");
        }
        return  repository.findAll(paging)
                .map(s -> conversionService.convert(s, PatientDto.class));
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
