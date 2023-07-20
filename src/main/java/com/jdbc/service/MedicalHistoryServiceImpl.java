package com.jdbc.service;

import com.jdbc.dao.EntityTransaction;
import com.jdbc.dao.api.MedicalHistoryDao;
import com.jdbc.dto.medical_history.MedicalHistoryCreateDto;
import com.jdbc.dto.medical_history.MedicalHistoryEditDto;
import com.jdbc.dto.medical_history.MedicalHistoryReadDto;
import com.jdbc.entity.Doctor;
import com.jdbc.entity.MedicalHistory;
import com.jdbc.entity.Patient;
import com.jdbc.mapper.mapper.*;
import com.jdbc.service.api.DoctorService;
import com.jdbc.service.api.MedicalHistoryService;
import com.jdbc.service.api.PatientService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
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
    private final DoctorEntityMapper doctorEntityMapper;
    private final PatientEntityMapper patientEntityMapper;
    public MedicalHistoryServiceImpl(MedicalHistoryDao dao,
                                     DoctorService doctorService,
                                     PatientService patientService ) {
        this.dao = dao;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.doctorEntityMapper = new DoctorEntityMapper();
        this.patientEntityMapper = new PatientEntityMapper();
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
        Doctor doctor = doctorService.findDoctorById(historyCreateDto.getDoctor())
                .map(doctorEntityMapper::map)
                .orElseThrow(() ->  new NoSuchElementException("Doctor not found "+ historyCreateDto.getDoctor()));
        Patient patient = patientService.findPatientById(historyCreateDto.getPatient())
                .map(patientEntityMapper::map)
                .orElseThrow(() ->  new NoSuchElementException("Patient not found "+ historyCreateDto.getPatient()));
            history.setUuid(UUID.randomUUID());
            history.setDoctor(doctor);
            history.setPatient(patient);
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
        checkUuid(id);
        return dao.findMedicalHistoryById(id)
                .map(readMapper::map);
    }

    @Override
    public void delete(UUID uuid) {
        checkUuid(uuid);
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
        checkUuid(uuid);
        //        Transactional
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(dao);
        try {

            Doctor doctor = doctorService.findDoctorById(history.getDoctor())
                    .map(doctorEntityMapper::map)
                    .orElseThrow(() ->  new NoSuchElementException("Doctor not found "+ history.getDoctor()));
            Patient patient = patientService.findPatientById(history.getPatient())
                    .map(patientEntityMapper::map)
                    .orElseThrow(() ->  new NoSuchElementException("Patient not found "+ history.getPatient()));
            MedicalHistory medicalHistory = dao.findMedicalHistoryById(uuid).map(s -> {
                        s.setDtUpdated(LocalDateTime.now());
                        s.setPatient(patient);
                        s.setDoctor(doctor);
                        s.setDiagnosis(history.getDiagnosis());
                        s.setTreatment(history.getTreatment());
                        return s;
                    })
                    .orElseThrow(() -> new RuntimeException("can not convert"));
            dao.update(uuid, medicalHistory);
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
    private void checkUuid(UUID uuid){
        if (uuid == null) throw  new RuntimeException("invalid uuid "+ uuid);
        dao.findMedicalHistoryById(uuid)
                .orElseThrow(() -> new RuntimeException("such uuid " + uuid + " is not exist"));
    }
}
