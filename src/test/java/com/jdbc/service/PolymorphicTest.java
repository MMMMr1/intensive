package com.jdbc.service;


import com.jdbc.dto.doctor.DoctorCreateDto;
import com.jdbc.dto.nurse.NurseCreateDto;
import static org.assertj.core.api.Assertions.*;

import com.jdbc.orm.SessionManager;
import com.jdbc.orm.fabrics.SessionFactorySingleton;
import com.jdbc.service.api.DoctorService;
import com.jdbc.service.api.NurseService;
import com.jdbc.service.fabrics.DoctorServiceSingleton;
import com.jdbc.service.fabrics.NurseServiceSingleton;
import org.hibernate.Session;
import org.junit.jupiter.api.*;

import java.beans.PropertyVetoException;

public class PolymorphicTest {
    private DoctorService doctorService;
    private NurseService nurseService;
    private SessionManager sessionManager;

    {
        try {
            doctorService = DoctorServiceSingleton.getInstance();
            nurseService = NurseServiceSingleton.getInstance();
            sessionManager = SessionFactorySingleton.getInstance();
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void init() {
        DoctorCreateDto doctorCreateDto = new DoctorCreateDto(
                "TestPatient",
                "TestPatient",
                "TestPatient",
                "TestPatient",
                "TestPatient"
        );
          doctorService.create(doctorCreateDto);
        NurseCreateDto nurseCreateDto = new NurseCreateDto(
                "TestNurse",
                "TestNurse",
                "TestNurse",
                "TestNurse",
                "TestNurse",
                "TestNurse"
        );
        nurseService.create(nurseCreateDto);
        try (Session session = sessionManager.getSession()) {
            session.getTransaction().begin();
            assertThat(session.createQuery("from Employee")
                    .getResultList().size()).isEqualTo(2);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }


        }
}
