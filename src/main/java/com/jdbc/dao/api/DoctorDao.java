package com.jdbc.dao.api;

import com.jdbc.entity.Doctor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DoctorDao extends Transactional{
    Long create (Doctor t);
    void update(Long id, Doctor t);
    void delete(Long id);
    List<Doctor> findAll();
    Optional<Doctor> findDoctorById(Long id);

}
