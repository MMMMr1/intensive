package com.jdbc.dao;

import com.jdbc.dao.api.PatientDao;
import com.jdbc.entity.Patient;
import com.jdbc.orm.SessionManager;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;
@Log4j2
public class PatientDaoImpl implements PatientDao {
    private final String SQL_CREATE = "INSERT INTO app.patients (uuid, lastname, firstname, surname, address, phone, medical_card_number, dt_created, dt_updated) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING uuid;";
    private final String SQL_UPDATE = "UPDATE  app.patients SET lastname = ?, firstname = ?, surname = ?, address = ?, phone = ?, medical_card_number = ?, dt_updated  = ? WHERE uuid = ?;";
    private final String SQL_DELETE = "DELETE FROM app.patients  WHERE uuid = ?;";
    private final String SQL_GET = "SELECT uuid, lastname, firstname, surname, address, phone, medical_card_number, dt_created, dt_updated FROM app.patients;";
    private final String SQL_GET_BY_ID = "SELECT uuid, lastname, firstname, surname, address, phone, medical_card_number, dt_created, dt_updated FROM app.patients WHERE uuid = ?;";
    private SessionManager sessionManager;
    private Session session;

    public PatientDaoImpl(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public UUID create(Patient patient) {
        UUID uuid = patient.getId();
        try {
//            if (session != null) {
                session.persist(patient);
//            log.info("persist "+  uuid);
                return uuid;
//            }else try {
//
//            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void update(UUID uuid, Patient patient) {
        try {
            session.update(patient);
            log.info("update "+ patient.getId());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void delete(UUID uuid) {
        try {
            Patient patient = session.load(Patient.class, uuid);
            session.delete(patient);
            log.info("delete "+ patient.getId());
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
            log.info("find ");
            session.getTransaction().commit();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Optional<Patient> findPatientById(UUID uuid) {
        Patient patient = null;
        try (Session session = sessionManager.getSession()) {
            patient = session.get(Patient.class, uuid);
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
