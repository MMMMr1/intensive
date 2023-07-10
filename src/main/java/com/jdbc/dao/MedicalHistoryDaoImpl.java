package com.jdbc.dao;

import com.jdbc.dao.api.MedicalHistoryDao;
import com.jdbc.entity.Doctor;
import com.jdbc.entity.MedicalHistory;
import com.jdbc.orm.SessionManager;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Log4j2
public class MedicalHistoryDaoImpl implements MedicalHistoryDao {
    private SessionManager sessionManager;
    private Session session;

    public MedicalHistoryDaoImpl(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }
    @Override
    public UUID create(MedicalHistory history) {
        try {
            session.persist(history);
            return history.getUuid();
        } catch (Exception e) {
            log.info("update "+ history);
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void update(UUID uuid, MedicalHistory history) {
        log.info("update "+ uuid);
        try {
            session.update(history);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void delete(UUID uuid) {
        log.info("delete "+ uuid);
        try {
            MedicalHistory medicalHistory = session.load(MedicalHistory.class, uuid);
            session.delete(medicalHistory);
            session.flush();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<MedicalHistory> findAll() {
        try (Session session = sessionManager.getSession()) {
            session.getTransaction().begin();
            List<MedicalHistory> list = session.createQuery("FROM MedicalHistory", MedicalHistory.class).getResultList();
            session.getTransaction().commit();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Optional<MedicalHistory> findMedicalHistoryById(UUID uuid) {
        try (Session session = sessionManager.getSession()) {
            MedicalHistory medicalHistory = session.get(MedicalHistory.class, uuid);
            return Optional.ofNullable(medicalHistory);
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
