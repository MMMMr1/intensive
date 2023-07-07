package com.jdbc.dao.api;

import com.jdbc.entity.Patient;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PatientDao extends Transactional{
    UUID create (Patient t);
    void update(UUID uuid, Patient t);
    void delete(UUID uuid);
    List<Patient> findAll();
    Optional<Patient> findPatientById(UUID id);

}
