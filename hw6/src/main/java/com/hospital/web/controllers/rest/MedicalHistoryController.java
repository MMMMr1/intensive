package com.hospital.web.controllers.rest;

import com.hospital.core.dto.MedicalHistoryDto;
import com.hospital.service.api.MedicalHistoryService;
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
@RequestMapping("/api/v1/histories")
public class MedicalHistoryController {
    private final MedicalHistoryService service;
    private static final Logger logger =
            LoggerFactory.getLogger(MedicalHistoryController.class);
//    @JsonView(View.InInfo.class)
    @RequestMapping(method = RequestMethod.POST)
    protected ResponseEntity<?> create(@RequestBody @Validated MedicalHistoryDto historyDto)   {
        logger.info("create medical history "+ historyDto);
        service.create(historyDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
//    @JsonView(View.OutInfo.class)
    @RequestMapping(method = RequestMethod.GET)
    protected ResponseEntity<Page<MedicalHistoryDto>> getAll(
            @RequestParam(name = "page", defaultValue = "0")  Integer page,
            @RequestParam(name = "size", defaultValue = "20") Integer size) {
        Pageable paging = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.getPage(paging));
    }
//    @JsonView(View.OutInfo.class)
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<MedicalHistoryDto> get(@PathVariable("id") Long id) {
        logger.info("get employee with "+ id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.findMedicalHistoryById(id));
    }
//    @JsonView(View.InInfo.class)
    @RequestMapping(path = "/{id}/dt_update/{dt_update}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable("id") Long id,
                                        @PathVariable("dt_update") Instant dtUpdate,
                                        @RequestBody @Validated MedicalHistoryDto historyDto) {
        service.update(id, dtUpdate, historyDto);
        logger.info("update medical history with "+ id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id ) {
        service.delete(id);
        logger.info("delete medical history with "+ id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
