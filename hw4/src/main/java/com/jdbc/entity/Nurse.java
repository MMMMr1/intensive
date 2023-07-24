package com.jdbc.entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "app.nurses")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Nurse extends Employee {
    @Column
    private String position;
    @Column
    private String blockfloor;
    @Column
    private String blockcode;

    public Nurse() {
    }

    public Nurse(Long id, String lastName, String firstName, String surName, LocalDateTime dtCreated, LocalDateTime dtUpdated, String position, String blockfloor, String blockcode) {
        super(id, lastName, firstName, surName, dtCreated, dtUpdated);
        this.position = position;
        this.blockfloor = blockfloor;
        this.blockcode = blockcode;
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