package com.hospital.service;

import com.hospital.core.dto.NurseDto;
import com.hospital.core.dto.PatientDto;
import com.hospital.core.exception.InvalidVersionException;
import com.hospital.core.exception.employee.EmployeeNotFoundException;

import com.hospital.entity.Nurse;
import com.hospital.entity.Patient;
import com.hospital.repository.api.NurseRepository;
import com.hospital.service.api.NurseService;
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
public class NurseServiceImpl implements NurseService {
    private final NurseRepository repository;
    private final ConversionService conversionService;
    private static final Logger logger =
            LoggerFactory.getLogger(NurseServiceImpl.class);


    @Transactional
    @Override
    public NurseDto create(NurseDto nurseDto) {
        if (!conversionService.canConvert(NurseDto.class, Nurse.class)) {
            throw new IllegalStateException("Can not convert NurseDto.class to Nurse.class");
        }
        return Optional.of(nurseDto)
                .map(s -> {
                    Nurse nurse = conversionService.convert(nurseDto, Nurse.class);
                    Instant dtCreated = Instant.now();
                    nurse.setDtCreated(dtCreated);
                    nurse.setDtUpdated(dtCreated);
                    return nurse;
                })
                .map(repository::save)
                .map(entity -> conversionService.convert(entity, NurseDto.class))
                .orElseThrow();
    }

    @Transactional
    @Override
    public Optional<NurseDto> update(Long id, Instant version, NurseDto nurse) {
        return repository.findById(id)
                .map(entity -> {
                    if (version.toEpochMilli() != entity.getDtUpdated().toEpochMilli()) {
                        logger.error("Can not update employee " + id + "invalid version " + version);
                        throw new InvalidVersionException("Invalid version");
                    }
                    copy(nurse, entity);
                    entity.setDtUpdated(Instant.now());
                    return entity;
                })
                .map(repository::saveAndFlush)
                .map(s -> conversionService.convert(s, NurseDto.class));
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
    public Nurse findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("There is no employee with such id"));
    }

    @Override
    public NurseDto findNurseById(Long id) {
        if (!conversionService.canConvert(Nurse.class, NurseDto.class)) {
            throw new IllegalStateException("Can not convert NurseDto.class to Nurse.class");
        }
        return repository.findById(id)
                .map(s -> conversionService.convert(s, NurseDto.class))
                .orElseThrow(() -> new EmployeeNotFoundException("There is no employee with such id"));
    }

    @Override
    public Page<NurseDto> getPage(Pageable paging) {
        if (!conversionService.canConvert(Nurse.class, NurseDto.class)) {
            throw new IllegalStateException("Can not convert NurseDto.class to Nurse.class");
        }
        return  repository.findAll(paging)
                .map(s -> conversionService.convert(s, NurseDto.class));
    }
    private void copy(NurseDto object, Nurse nurse) {
        nurse.setFirstName(object.getFirstName());
        nurse.setLastName(object.getLastName());
        nurse.setSurName(object.getSurName());
        nurse.setDepartment(object.getDepartment());
        nurse.setPosition(object.getPosition());
        nurse.setBlockfloor(object.getBlockfloor());
        nurse.setBlockcode(object.getBlockcode());
    }
}
