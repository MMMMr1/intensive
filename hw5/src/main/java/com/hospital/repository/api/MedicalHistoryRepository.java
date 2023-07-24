package com.hospital.repository.api;

import com.hospital.entity.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Long>,
        PagingAndSortingRepository<MedicalHistory,Long> {


}
