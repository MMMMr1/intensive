package com.hospital.service.api;

import com.hospital.core.dto.NurseDto;
import com.hospital.entity.Nurse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Optional;

public interface NurseService {
    NurseDto create (@NotNull @Valid NurseDto nurse );
    Nurse findById(@NotNull Long id);
    NurseDto findNurseById(@NotNull Long id);
    boolean delete(@NotNull Long id);
    Optional<NurseDto> update(@NotNull Long id, @NotNull Instant version, @NotNull @Valid NurseDto nurse);
    Page<NurseDto> getPage(Pageable paging);


}
