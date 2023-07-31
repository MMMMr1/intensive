package com.hospital.web.controllers.mvc;

import com.hospital.entity.Employee;
import com.hospital.service.api.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class EmployeeController {
    private final EmployeeService service;


    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    protected String getAll(Model model,
                            @RequestParam("page") Optional<Integer> page,
                            @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(0);
        int pageSize = size.orElse(20);
        Pageable paging = PageRequest.of(currentPage, pageSize);
        Page<Employee> employees = service.getPage(paging);
        model.addAttribute("employees", employees);
        return "employee/employees";
    }
    @RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
    protected String get(@PathVariable("id") Long id,
                         Model model) {
        return service.findById(id)
                .map(employee -> {
                    model.addAttribute("employee", employee);


                    return "employee/employee";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}

