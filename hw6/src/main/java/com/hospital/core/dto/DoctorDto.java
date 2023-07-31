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
public class DoctorDto {
//    @JsonView(View.OutInfo.class)
    private Long id;
//    @JsonView(View.InInfo.class)
    private String lastName;
//    @JsonView(View.InInfo.class)
    private String firstName;
//    @JsonView(View.InInfo.class)
    private String surName;
//    @JsonView(View.InInfo.class)
    private String position;
//    @JsonView(View.InInfo.class)
    private String department;
//    @JsonView(View.OutInfo.class)
    @JsonProperty("work_hours")
    private Integer workHours;
//    @JsonView(View.OutInfo.class)
    @JsonSerialize(converter = CustomInstantConverter.Serializer.class)
    @JsonProperty("dt_created")
    private Instant dtCreated;
    @JsonSerialize(converter = CustomInstantConverter.Serializer.class)
//    @JsonView(View.OutInfo.class)
    @JsonProperty("dt_updated")
    private Instant dtUpdated;
}
