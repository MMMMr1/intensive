package com.hospital.core.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hospital.core.View;
import com.hospital.core.converter.jackson.CustomInstantConverter;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class MedicalHistoryDto {
//    @JsonView(View.OutInfo.class)
    private Long id;
//    @JsonView(View.InInfo.class)
    @JsonProperty("patient")
    private Long patient;
//    @JsonView(View.InInfo.class)
    @JsonProperty("doctor")
    private Long doctor;
//    @JsonView(View.InInfo.class)
    @JsonProperty("diagnosis")
    private String diagnosis;
//    @JsonView(View.InInfo.class)
    @JsonProperty("treatment")
    private String treatment;
//    @JsonView(View.OutInfo.class)
    @JsonSerialize(converter = CustomInstantConverter.Serializer.class)
    @JsonProperty("dt_created")
    private Instant dtCreated;
//    @JsonView(View.OutInfo.class)
    @JsonSerialize(converter = CustomInstantConverter.Serializer.class)
    @JsonProperty("dt_updated")
    private Instant dtUpdated;
}
