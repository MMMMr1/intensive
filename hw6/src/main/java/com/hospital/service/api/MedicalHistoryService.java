package com.hospital.service.api;
import com.hospital.core.dto.MedicalHistoryDto;
import com.hospital.entity.MedicalHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Optional;

public interface MedicalHistoryService {
    MedicalHistoryDto create (@NotNull @Valid MedicalHistoryDto medicalHistory);
    MedicalHistory findById(@NotNull Long id);
    MedicalHistoryDto findMedicalHistoryById(@NotNull Long id);
    boolean delete(@NotNull Long id);
    Optional<MedicalHistoryDto> update(@NotNull Long id, @NotNull Instant dtUpdate, @NotNull @Valid MedicalHistoryDto medicalHistory);
    Page<MedicalHistoryDto> getPage(Pageable paging);
}
