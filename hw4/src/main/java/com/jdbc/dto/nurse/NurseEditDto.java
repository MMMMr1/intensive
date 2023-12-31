package com.jdbc.dto.nurse;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NurseEditDto {
    @JsonProperty("id")
    private Long uuid;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("surName")
    private String surName;
    @JsonProperty("position")
    private String position;
    @JsonProperty("blockfloor")
    private String blockfloor;
    @JsonProperty("blockcode")
    private String blockcode;
    public NurseEditDto() {
    }

    public NurseEditDto(Long uuid,
                        String lastName,
                        String firstName,
                        String surName,
                        String position,
                        String blockfloor,
                        String blockcode) {
        this.uuid = uuid;
        this.lastName = lastName;
        this.firstName = firstName;
        this.surName = surName;
        this.position = position;
        this.blockfloor = blockfloor;
        this.blockcode = blockcode;
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
}
