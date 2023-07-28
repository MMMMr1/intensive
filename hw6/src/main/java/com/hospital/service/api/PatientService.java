package com.hospital.service.api;


import com.hospital.core.dto.PatientDto;
import com.hospital.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Optional;

public interface PatientService  {
    PatientDto create (@NotNull @Valid PatientDto patient);
    Patient findById(@NotNull Long id);
    PatientDto findPatentById(@NotNull Long id);
    boolean delete(@NotNull Long id);
    Optional<PatientDto> update(@NotNull Long id, @NotNull Instant version, @NotNull @Valid PatientDto patient);
    Page<PatientDto> getPage(Pageable paging);
}
