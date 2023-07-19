package com.jdbc.service;


import com.jdbc.dao.EntityTransaction;
import com.jdbc.dao.api.DoctorDao;
import com.jdbc.dao.fabric.DoctorDaoSingleton;
import com.jdbc.dto.doctor.DoctorCreateDto;
import com.jdbc.dto.nurse.NurseCreateDto;
import com.jdbc.entity.Doctor;
import com.jdbc.orm.SessionManager;
import com.jdbc.orm.fabrics.SessionFactorySingleton;
import com.jdbc.service.api.DoctorService;
import com.jdbc.service.api.NurseService;
import com.jdbc.service.fabrics.DoctorServiceSingleton;
import com.jdbc.service.fabrics.NurseServiceSingleton;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.junit.jupiter.api.Test;

import java.beans.PropertyVetoException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;
@Log4j2
public class SecondLevelCashTest {
    private DoctorService service;
    private DoctorDao dao;
    private SessionManager sessionManager;

    {
        try {
            service = DoctorServiceSingleton.getInstance();
            dao = DoctorDaoSingleton.getInstance();
            sessionManager = SessionFactorySingleton.getInstance();
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void init() {
        int min = 1;
        int max = 1000000;
        EntityTransaction transaction = new EntityTransaction();
        transaction.initTransaction(dao);
        try {
//        for (int i = 0; i < 1000000; i++) {
//            dao.create(new Doctor(
//                    null,
//                    "Testdoctor " + i,
//                    "Petrov" + i,
//                    "TestPatient",
//                    "TestPatient",
//                    "TestPatient",
//                    new Random().ints(min, max + 1)
//                            .findFirst().getAsInt(),
//                    LocalDateTime.now(),
//                    LocalDateTime.now()));
//        }
            List<Doctor> doctorByWorkHours = dao.findDoctorByWorkHours(2000);

            System.out.println(doctorByWorkHours.size());
        transaction.commit();
    } catch (RuntimeException e) {
        transaction.rollback();
        throw new RuntimeException("Transaction create rollback"+ e.getMessage());
    } finally {
        transaction.endTransaction();
    }
    }

}
