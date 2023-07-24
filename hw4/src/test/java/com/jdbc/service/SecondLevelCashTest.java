package com.jdbc.service;


import com.jdbc.dao.EntityTransaction;
import com.jdbc.dao.api.DoctorDao;
import com.jdbc.dao.fabric.DoctorDaoSingleton;
import com.jdbc.entity.Doctor;
import com.jdbc.orm.SessionManager;
import com.jdbc.orm.fabrics.SessionFactorySingleton;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.beans.PropertyVetoException;
import java.util.List;

@Log4j2
public class SecondLevelCashTest {
    private DoctorDao dao;
    private SessionManager sessionManager;

    {
        try {
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
