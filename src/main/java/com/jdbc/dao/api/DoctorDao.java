package com.jdbc.dao.api;

import com.jdbc.entity.Doctor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DoctorDao extends Transactional{
    UUID create (Doctor t);
    void update(UUID uuid, Doctor t);
    void delete(UUID uuid);
    List<Doctor> findAll();
    Optional<Doctor> findDoctorById(UUID id);

}
