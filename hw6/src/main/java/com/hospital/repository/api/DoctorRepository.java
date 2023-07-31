package com.hospital.repository.api;

import com.hospital.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long>, PagingAndSortingRepository<Doctor,Long> {

    @Query("select d from Doctor  d WHERE  d.workHours > :minWorkHours")
    List<Doctor> findByWorkHours(@Param("minWorkHours") Integer workHours);
}
