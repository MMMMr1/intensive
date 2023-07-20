package com.jdbc.service;

import com.jdbc.dao.EntityTransaction;
import com.jdbc.dao.api.NurseDao;
import com.jdbc.dto.nurse.NurseCreateDto;
import com.jdbc.dto.nurse.NurseEditDto;
import com.jdbc.dto.nurse.NurseReadDto;
import com.jdbc.entity.Nurse;
import com.jdbc.mapper.mapper.NurseCreateMapper;
import com.jdbc.mapper.mapper.NurseEditMapper;
import com.jdbc.mapper.mapper.NurseReadMapper;
import com.jdbc.service.api.NurseService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class NurseServiceImpl implements NurseService {
    private final NurseDao dao;
    private final NurseCreateMapper createMapper;
    private final NurseEditMapper editMapper;
    private final NurseReadMapper readMapper;
    public NurseServiceImpl(NurseDao dao) {
        this.dao = dao;
        createMapper = new NurseCreateMapper();
        editMapper = new NurseEditMapper();
        readMapper = new NurseReadMapper();
    }
    @Override
    public Long create(NurseCreateDto nurseDto) {
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(dao);
        Long uuid;
        try {
            Nurse nurse = createMapper.map(nurseDto);
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
    @Override
    public void delete(Long uuid) {
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
    public void update(Long uuid, NurseEditDto nurse) {
        checkUuid(uuid);
        //        Transactional
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(dao);
        try {
            Nurse map = editMapper.map(nurse);
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
    public List<NurseReadDto> findAll() {
        return dao.findAll().stream()
                .map(readMapper::map)
                .collect(Collectors.toList());
    }
    private void checkUuid(Long uuid){
        if (uuid <= 0 ) throw  new RuntimeException("invalid uuid "+ uuid);
        dao.findNurseById(uuid).orElseThrow(() -> new RuntimeException("such uuid " + uuid + " is not exist"));
    }
}
