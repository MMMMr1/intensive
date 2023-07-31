package com.hospital.repository.api;

import com.hospital.entity.Doctor;
import com.hospital.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>, PagingAndSortingRepository<Employee,Long> {


}
