package com.hospital.entity;

import javax.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(schema = "app", name = "nurses")
public class Nurse extends Employee {

    @Column
    private String blockfloor;
    @Column
    private String blockcode;
}
