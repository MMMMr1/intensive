package com.hospital.web.controllers.rest;

import com.hospital.core.dto.DoctorDto;
import com.hospital.service.api.DoctorService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {
    private final DoctorService service;
    private static final Logger logger =
            LoggerFactory.getLogger(DoctorController.class);
    @RequestMapping(method = RequestMethod.POST)
    protected ResponseEntity<?> create(@RequestBody @Validated DoctorDto doctor)   {
        logger.info("create  employee "+ doctor);
        service.create(doctor);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @RequestMapping(method = RequestMethod.GET)
    protected ResponseEntity<Page<DoctorDto>> getAll(
            @RequestParam(name = "page", defaultValue = "0")  Integer page,
            @RequestParam(name = "size", defaultValue = "20") Integer size) {
        Pageable paging = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getPage(paging));
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<DoctorDto> get(@PathVariable("id") Long id) {
        logger.info("get employee with "+ id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.findDoctorById(id));
    }
    @RequestMapping(path = "/{id}/dt_update/{dt_update}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable("id") Long id,
                                        @PathVariable("dt_update") Instant dtUpdate,
                                        @RequestBody @Validated DoctorDto doctor) {
        service.update(id, dtUpdate, doctor);
        logger.info("update employee with "+ id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id ) {
        service.delete(id);
        logger.info("delete user with "+ id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
