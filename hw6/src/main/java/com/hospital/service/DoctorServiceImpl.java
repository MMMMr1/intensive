package com.hospital.service;

import com.hospital.core.dto.DoctorDto;
import com.hospital.core.exception.employee.EmployeeNotFoundException;
import com.hospital.core.exception.InvalidVersionException;
import com.hospital.entity.Doctor;
import com.hospital.repository.api.DoctorRepository;
import com.hospital.service.api.DoctorService;
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
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository repository;
    private final ConversionService conversionService;


    @Transactional
    @Override
    public DoctorDto create(DoctorDto doctorDto) {
        if (!conversionService.canConvert(DoctorDto.class, Doctor.class)) {
            throw new IllegalStateException("Can not convert DoctorDto.class to Doctor.class");
        }
        return Optional.of(doctorDto)
                .map(s -> {
                    Doctor doctor = conversionService.convert(doctorDto, Doctor.class);
                    Instant dtCreated = Instant.now();
                    doctor.setDtCreated(dtCreated);
                    doctor.setDtUpdated(dtCreated);
                    return doctor;
                })
                .map(repository::save)
                .map(entity -> conversionService.convert(entity, DoctorDto.class))
                .get();
    }

    @Transactional
    @Override
    public Optional<DoctorDto> update(Long id, Instant version, DoctorDto doctor) {
        return repository.findById(id)
                .map(doctorEntity -> {
                    if (version.toEpochMilli() != doctorEntity.getDtUpdated().toEpochMilli()) {

                        throw new InvalidVersionException("Invalid version");
                    }
                    copy(doctor, doctorEntity);
                    doctorEntity.setDtUpdated(Instant.now());
                    return doctorEntity;
                })
                .map(repository::saveAndFlush)
                .map(s -> conversionService.convert(s, DoctorDto.class));
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
    public Doctor findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("There is no user with such id"));
    }

    @Override
    public DoctorDto findDoctorById(Long id) {
        if (!conversionService.canConvert(Doctor.class, DoctorDto.class)) {
            throw new IllegalStateException("Can not convert DoctorDto.class to Doctor.class");
        }
        return repository.findById(id)
                .map(s -> conversionService.convert(s, DoctorDto.class))
                .orElseThrow(() -> new EmployeeNotFoundException("There is no user with such id"));

    }

    @Override
    public Page<DoctorDto> getPage(Pageable paging) {
        if (!conversionService.canConvert(Doctor.class, DoctorDto.class)) {
            throw new IllegalStateException("Can not convert DoctorDto.class to Doctor.class");
        }
        return  repository.findAll(paging)
                .map(s -> conversionService.convert(s, DoctorDto.class));
    }


    private void copy(DoctorDto object, Doctor doctor) {
        doctor.setFirstName(object.getFirstName());
        doctor.setLastName(object.getLastName());
        doctor.setSurName(object.getSurName());
        doctor.setPosition(object.getPosition());
        doctor.setDepartment(object.getDepartment());
        doctor.setWorkHours(object.getWorkHours());
    }
}
