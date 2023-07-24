package com.hospital.service.api;

import com.hospital.core.dto.DoctorDto;
import com.hospital.entity.Doctor;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.Optional;

public interface DoctorService {
    DoctorDto create (@NotNull @Valid DoctorDto doctor );
    Doctor findById(@NotNull Long id);
    DoctorDto findDoctorById(@NotNull Long id);

    boolean delete(@NotNull Long id);
    Optional<DoctorDto> update(@NotNull Long id, @NotNull Instant dtUpdate, @NotNull @Valid DoctorDto doctor);
    Page<DoctorDto> getPage(Pageable paging);
}
