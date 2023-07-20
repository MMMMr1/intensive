package com.jdbc.dao;

import com.jdbc.dao.api.DoctorDao;
import com.jdbc.entity.Doctor;
import com.jdbc.entity.Employee;
import com.jdbc.entity.Patient;
import com.jdbc.orm.SessionManager;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.stat.Statistics;

import java.util.*;
@Log4j2
public class DoctorDaoImpl implements DoctorDao {
    private SessionManager sessionManager;
    private Session session;
    public DoctorDaoImpl(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }
    @Override
    public Long create(Doctor doctor) {
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
    public void update(Long id, Doctor doctor) {
        log.info("update "+ id);
        try {
            session.update(doctor);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    @Override
    public void delete(Long id) {
        log.info("delete "+ id);
        try {
            Doctor doctor = session.load(Doctor.class, id);
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
    public Optional<Doctor> findDoctorById(Long id) {
        try (Session session = sessionManager.getSession()) {
            Doctor doctor = (Doctor) session.get(Employee.class, id);
            return Optional.ofNullable(doctor);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
    public List<Doctor> findDoctorByWorkHours(Integer minWorkHours) {
        try (Session session = sessionManager.getSession()) {
            session.getTransaction().begin();
            List<Doctor> list = session.createQuery("FROM Doctor  WHERE  workHours > :minWorkHours", Doctor.class)
                    .setParameter("minWorkHours",minWorkHours)
                    .setCacheable(true)
                    .getResultList();
            session.getTransaction().commit();
            return list;
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
