package com.hospital.entity;

import javax.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(schema = "app", name = "doctors")
public class Doctor extends Employee {

    @Column(name = "workhours")
    private Integer workHours;
    @OneToMany(mappedBy = "doctor",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<MedicalHistory> patients = new ArrayList<>();

}
