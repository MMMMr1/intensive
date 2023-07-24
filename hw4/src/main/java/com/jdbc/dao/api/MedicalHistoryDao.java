package com.jdbc.dao.api;

import com.jdbc.entity.MedicalHistory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MedicalHistoryDao extends Transactional{
    UUID create (MedicalHistory t);
    void update(UUID uuid, MedicalHistory t);
    void delete(UUID uuid);
    List<MedicalHistory> findAll();
    Optional<MedicalHistory> findMedicalHistoryById(UUID id);

}
