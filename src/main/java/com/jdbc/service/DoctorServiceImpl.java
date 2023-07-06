package com.jdbc.service;

import com.jdbc.dao.EntityTransaction;
import com.jdbc.dao.api.DoctorDao;
import com.jdbc.dto.doctor.DoctorCreateDto;
import com.jdbc.dto.doctor.DoctorEditDto;
import com.jdbc.dto.doctor.DoctorReadDto;
import com.jdbc.entity.Doctor;
import com.jdbc.mapper.mapper.*;
import com.jdbc.service.api.DoctorService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class DoctorServiceImpl implements DoctorService {

    private final DoctorDao dao;
    private final DoctorCreateMapper createMapper;
    private final DoctorEditMapper editMapper;
    private final DoctorReadMapper readMapper;

    public DoctorServiceImpl(DoctorDao dao) {
        this.dao = dao;
        this.createMapper = new DoctorCreateMapper();
        this.readMapper = new DoctorReadMapper();
        this.editMapper = new DoctorEditMapper();
    }

    @Override
    public void create(DoctorCreateDto doctorCreateDto) {
//        Transactional
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(dao);
        try {
            Doctor doctor = createMapper.map(doctorCreateDto);
            doctor.setId(UUID.randomUUID());
            doctor.setDtCreated(LocalDateTime.now());
            doctor.setDtUpdated(LocalDateTime.now());
            dao.create(doctor);
            transaction.commit();
        } catch (RuntimeException e) {
            transaction.rollback();
            throw new RuntimeException(e);
        } finally {
            transaction.endTransaction();
        }
    }

    @Override
    public Optional<DoctorReadDto> findDoctorById(UUID id) {
        return dao.findDoctorById(id)
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
    public void update(UUID uuid, DoctorEditDto patient) {
        //        Transactional
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(dao);
        try {
            Doctor map = editMapper.map(patient);
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
    public List<DoctorReadDto> findAll() {
        return dao.findAll().stream()
                .map(readMapper::map)
                .collect(Collectors.toList());
    }
}
