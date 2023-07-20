package com.jdbc.dto.nurse;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class NurseReadDto {
    private Long uuid;
    private String lastName;
    private String firstName;
    private String surName;
    private String position;
    private String blockfloor;
    private String blockcode;
    private LocalDateTime dtCreated;
    private LocalDateTime dtUpdated;
    public NurseReadDto() {
    }

    public NurseReadDto(Long uuid,
                        String lastName,
                        String firstName,
                        String surName,
                        String position,
                        String blockfloor,
                        String blockcode,
                        LocalDateTime dtCreated,
                        LocalDateTime dtUpdated) {
        this.uuid = uuid;
        this.lastName = lastName;
        this.firstName = firstName;
        this.surName = surName;
        this.position = position;
        this.blockfloor = blockfloor;
        this.blockcode = blockcode;
        this.dtCreated = dtCreated;
        this.dtUpdated = dtUpdated;
    }

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getBlockfloor() {
        return blockfloor;
    }

    public void setBlockfloor(String blockfloor) {
        this.blockfloor = blockfloor;
    }

    public String getBlockcode() {
        return blockcode;
    }

    public void setBlockcode(String blockcode) {
        this.blockcode = blockcode;
    }

    public LocalDateTime getDtCreated() {
        return dtCreated;
    }

    public void setDtCreated(LocalDateTime dtCreated) {
        this.dtCreated = dtCreated;
    }

    public LocalDateTime getDtUpdated() {
        return dtUpdated;
    }

    public void setDtUpdated(LocalDateTime dtUpdated) {
        this.dtUpdated = dtUpdated;
    }
}
