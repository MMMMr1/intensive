package com.jdbc.dao.api;

import com.jdbc.entity.Doctor;
import com.jdbc.entity.Nurse;

import java.util.List;
import java.util.Optional;

public interface NurseDao extends Transactional{
    Long create (Nurse t);
    void update(Long id, Nurse t);
    void delete(Long id);
    List<Nurse> findAll();
    Optional<Nurse> findNurseById(Long id);

}
