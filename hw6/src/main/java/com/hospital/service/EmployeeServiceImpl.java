package com.hospital.service;

import com.hospital.core.dto.DoctorDto;
import com.hospital.core.exception.InvalidVersionException;
import com.hospital.core.exception.employee.EmployeeNotFoundException;
import com.hospital.entity.Doctor;
import com.hospital.entity.Employee;
import com.hospital.repository.api.DoctorRepository;
import com.hospital.repository.api.EmployeeRepository;
import com.hospital.service.api.DoctorService;
import com.hospital.service.api.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;
    @Override
    public Optional <Employee> findById(Long id) {
        return repository.findById(id);
    }


    @Override
    public Page<Employee> getPage(Pageable paging) {
 
        return repository.findAll(paging);
    }

}
