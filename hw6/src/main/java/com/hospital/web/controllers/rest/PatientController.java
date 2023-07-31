package com.hospital.web.controllers.rest;

import com.hospital.core.dto.PatientDto;
import com.hospital.service.api.PatientService;
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
@RequestMapping("/api/v1/patients")
public class PatientController {
    private final PatientService service;
    private static final Logger logger =
            LoggerFactory.getLogger(PatientController.class);
//    @JsonView(View.InInfo.class)
    @RequestMapping(method = RequestMethod.POST)
    protected ResponseEntity<?> create(@RequestBody @Validated PatientDto patientDto)   {
        logger.info("create  patient "+ patientDto);
        service.create(patientDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

//    @JsonView(View.OutInfo.class)
    @RequestMapping(method = RequestMethod.GET)
    protected ResponseEntity<Page<PatientDto>> getAll(
            @RequestParam(name = "page", defaultValue = "0")  Integer page,
            @RequestParam(name = "size", defaultValue = "20") Integer size) {
        Pageable paging = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getPage(paging));
    }
//    @JsonView(View.OutInfo.class)
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PatientDto> get(@PathVariable("id") Long id) {
        logger.info("get employee with "+ id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.findPatentById(id));
    }
//    @JsonView(View.InInfo.class)
    @RequestMapping(path = "/{id}/dt_update/{dt_update}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable("id") Long id,
                                        @PathVariable("dt_update") Instant dtUpdate,
                                        @RequestBody @Validated PatientDto patientDto) {
        service.update(id, dtUpdate, patientDto);
        logger.info("update patient with "+ id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id ) {
        service.delete(id);
        logger.info("delete patient with "+ id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
