package com.jdbc.service;

import com.jdbc.dao.EntityTransaction;
import com.jdbc.dao.api.MedicalHistoryDao;
import com.jdbc.dto.doctor.DoctorReadDto;
import com.jdbc.dto.patient.PatientReadDto;
import com.jdbc.dto.medical_history.MedicalHistoryCreateDto;
import com.jdbc.dto.medical_history.MedicalHistoryEditDto;
import com.jdbc.dto.medical_history.MedicalHistoryReadDto;
import com.jdbc.entity.MedicalHistory;
import com.jdbc.mapper.mapper.*;
import com.jdbc.service.api.DoctorService;
import com.jdbc.service.api.MedicalHistoryService;
import com.jdbc.service.api.PatientService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class MedicalHistoryServiceImpl implements MedicalHistoryService {

    private final MedicalHistoryDao dao;
    private final DoctorService doctorService;
    private final PatientService patientService;
    private final MedicalHistoryCreateMapper createMapper;
    private final MedicalHistoryEditMapper editMapper;
    private final MedicalHistoryReadMapper readMapper;

    public MedicalHistoryServiceImpl(MedicalHistoryDao dao, DoctorService doctorService, PatientService patientService) {
        this.dao = dao;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.createMapper = new MedicalHistoryCreateMapper();
        this.readMapper = new MedicalHistoryReadMapper();
        this.editMapper = new MedicalHistoryEditMapper();
    }

    @Override
    public UUID create(MedicalHistoryCreateDto historyCreateDto) {
//        Transactional
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(dao);
        UUID uuid;
        try {
            MedicalHistory history = createMapper.map(historyCreateDto);
            Optional<DoctorReadDto> doctorById = doctorService.findDoctorById(historyCreateDto.getDoctor());
            Optional<PatientReadDto> patientById = patientService.findPatientById(historyCreateDto.getPatient());
            if (doctorById.equals(null) || patientById.equals(null)){
                throw new RuntimeException("doctor or patient is not present");
            }
            history.setUuid(UUID.randomUUID());
            history.setDtCreated(LocalDateTime.now());
            history.setDtUpdated(LocalDateTime.now());
            uuid = dao.create(history);
            transaction.commit();
            return uuid;
        } catch (RuntimeException e) {
            transaction.rollback();
            throw new RuntimeException("Transaction create rollback"+ e.getMessage());
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public Optional<MedicalHistoryReadDto> findMedicalHistoryById(UUID id) {
        return dao.findMedicalHistoryById(id)
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
            throw new RuntimeException("Transaction delete rollback"+ e.getMessage());
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public void update(UUID uuid, MedicalHistoryEditDto history) {
        //        Transactional
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(dao);
        try {
            MedicalHistory map = editMapper.map(history);
            map.setDtUpdated(LocalDateTime.now());
            dao.update(uuid, map);
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw new RuntimeException("Transaction update rollback"+ e.getMessage());
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public List<MedicalHistoryReadDto> findAll() {
        return dao.findAll().stream()
                .map(readMapper::map)
                .collect(Collectors.toList());
    }
}
