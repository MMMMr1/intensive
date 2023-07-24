package com.jdbc.dao;

import com.jdbc.dao.api.PatientDao;
import com.jdbc.entity.Patient;
import com.jdbc.orm.SessionManager;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import java.util.*;
@Log4j2
public class PatientDaoImpl implements PatientDao {
    private SessionManager sessionManager;
    private Session session;

    public PatientDaoImpl(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public UUID create(Patient patient) {
        try {
                session.persist(patient);
                return patient.getId();
        } catch (Exception e) {
            log.info("update "+ patient);
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void update(UUID uuid, Patient patient) {
        log.info("update "+ uuid);
        try {
            session.update(patient);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void delete(UUID uuid) {
        log.info("delete "+ uuid);
        try {
            Patient patient = session.load(Patient.class, uuid);
            session.delete(patient);
            session.flush();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Patient> findAll() {
        try (Session session = sessionManager.getSession()) {
            session.getTransaction().begin();
            List<Patient> list = session.createQuery("FROM Patient", Patient.class).getResultList();
            session.getTransaction().commit();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Optional<Patient> findPatientById(UUID uuid) {
        try (Session session = sessionManager.getSession()) {
            Patient patient = session.get(Patient.class, uuid);
            return Optional.ofNullable(patient);
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
