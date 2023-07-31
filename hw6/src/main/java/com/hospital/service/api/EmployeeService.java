package com.hospital.service.api;


import com.hospital.entity.Doctor;
import com.hospital.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotNull;
import java.util.Optional;

public interface EmployeeService {
     Page<Employee> getPage(Pageable paging);
     Optional<Employee> findById(@NotNull Long id);
}
