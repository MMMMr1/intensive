package com.jdbc.service;

import com.jdbc.dao.EntityTransaction;
import com.jdbc.dao.api.NurseDao;
import com.jdbc.dto.doctor.DoctorReadDto;
import com.jdbc.dto.nurse.NurseCreateDto;
import com.jdbc.entity.Nurse;
import com.jdbc.mapper.mapper.NurseCreateMapper;
import com.jdbc.service.api.NurseService;

import java.time.LocalDateTime;
import java.util.Optional;

public class NurseServiceImpl implements NurseService {

    private final NurseDao dao;
    private final NurseCreateMapper createMapper;


    public NurseServiceImpl(NurseDao dao) {
        this.dao = dao;
        createMapper = new NurseCreateMapper();
    }

    @Override
    public Long create(NurseCreateDto nurseDto) {
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(dao);
        Long uuid;
        try {
            Nurse nurse = createMapper.map(nurseDto);

//            doctor.setUuid(UUID.randomUUID());
            nurse.setDtCreated(LocalDateTime.now());
            nurse.setDtUpdated(LocalDateTime.now());
            uuid = dao.create(nurse);
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
    public Optional<Nurse> findNurseById(Long id) {
            return dao.findNurseById(id);
    }
}
