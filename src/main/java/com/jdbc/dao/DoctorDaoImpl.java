package com.jdbc.dao;

import com.jdbc.dao.api.DoctorDao;
import com.jdbc.entity.Doctor;
import com.jdbc.entity.Patient;
import com.jdbc.orm.SessionManager;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;

import java.util.*;
@Log4j2
public class DoctorDaoImpl implements DoctorDao {
    private SessionManager sessionManager;
    private Session session;

    public DoctorDaoImpl(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public UUID create(Doctor doctor) {
        try {
            session.persist(doctor);
            return doctor.getId();
        } catch (Exception e) {
            log.info("update "+ doctor);
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void update(UUID uuid, Doctor doctor) {
        log.info("update "+ uuid);
        try {
            session.update(doctor);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void delete(UUID uuid) {
        log.info("delete "+ uuid);
        try {
            Doctor doctor = session.load(Doctor.class, uuid);
            session.delete(doctor);
            session.flush();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Doctor> findAll() {
        try (Session session = sessionManager.getSession()) {
            session.getTransaction().begin();
            List<Doctor> list = session.createQuery("FROM Doctor", Doctor.class).getResultList();
            session.getTransaction().commit();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Optional<Doctor> findDoctorById(UUID uuid) {
        try (Session session = sessionManager.getSession()) {
            Doctor doctor = session.get(Doctor.class, uuid);
            return Optional.ofNullable(doctor);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    @Override
    public void setSession(Session session) {
        this.session = session;
    }
}
