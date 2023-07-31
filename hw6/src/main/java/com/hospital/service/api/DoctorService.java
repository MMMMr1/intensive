package com.hospital.service.api;

import com.hospital.core.dto.DoctorDto;
import com.hospital.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
