package com.jdbc.service;

import com.jdbc.dao.EntityTransaction;
import com.jdbc.dao.api.PatientDao;
import com.jdbc.dto.patient.PatientCreateDto;
import com.jdbc.dto.patient.PatientEditDto;
import com.jdbc.dto.patient.PatientReadDto;
import com.jdbc.entity.Patient;
import com.jdbc.mapper.mapper.PatientCreateMapper;
import com.jdbc.mapper.mapper.PatientEditMapper;
import com.jdbc.mapper.mapper.PatientReadMapper;
import com.jdbc.service.api.PatientService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class PatientServiceImpl implements PatientService {

    private final PatientDao dao;
    private final PatientCreateMapper createMapper;
    private final PatientEditMapper editMapper;
    private final PatientReadMapper readMapper;

    public PatientServiceImpl(PatientDao dao) {
        this.dao = dao;
        this.createMapper = new PatientCreateMapper();
        this.readMapper = new PatientReadMapper();
        this.editMapper = new PatientEditMapper();
    }

    @Override
    public UUID create(PatientCreateDto patientDto) {
//        Transactional
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(dao);
        UUID uuid;
        try {
            Patient patient = createMapper.map(patientDto);
            patient.setId(UUID.randomUUID());
            patient.setDtCreated(LocalDateTime.now());
            patient.setDtUpdated(LocalDateTime.now());
            uuid = dao.create(patient);
            transaction.commit();
            return uuid;
        } catch (RuntimeException e) {
            transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public Optional<PatientReadDto> findPatientById(UUID id) {
        return dao.findPatientById(id)
                .map(readMapper::map);
    }

    @Override
    public void delete(UUID uuid) {
        //        Transactional
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(dao);
        try {
            dao.delete(uuid);
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public void update(UUID uuid, PatientEditDto patient) {
        //        Transactional
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(dao);
        try {
            Patient map = editMapper.map(patient);
            map.setDtUpdated(LocalDateTime.now());
            dao.update(uuid, map);
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public List<PatientReadDto> findAll() {
        return dao.findAll().stream()
                .map(readMapper::map)
                .collect(Collectors.toList());
    }
}
