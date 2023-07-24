package com.jdbc.dao;

import com.jdbc.dao.api.NurseDao;
import com.jdbc.entity.Employee;
import com.jdbc.entity.Nurse;
import com.jdbc.orm.SessionManager;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;

import java.util.List;
import java.util.Optional;

@Log4j2
public class NurseDaoImpl implements NurseDao {
    private SessionManager sessionManager;
    private Session session;

    public NurseDaoImpl(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public Long create(Nurse nurse) {
        try {
            session.persist(nurse);
            return nurse.getId();
        } catch (Exception e) {
            log.info("update "+ nurse);
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void update(Long id,Nurse nurse) {
        log.info("update "+ id);
        try {
            session.update(nurse);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void delete(Long id) {
        log.info("delete "+ id);
        try {
            Nurse nurse = session.load(Nurse.class, id);
            session.delete(nurse);
            session.flush();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Nurse> findAll() {
        try (Session session = sessionManager.getSession()) {
            session.getTransaction().begin();
            List<Nurse> list = session.createQuery("FROM Nurse", Nurse.class).getResultList();
            session.getTransaction().commit();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Optional<Nurse> findNurseById(Long id) {
        try (Session session = sessionManager.getSession()) {
            Nurse nurse = (Nurse) session.get(Employee.class, id);
            return Optional.ofNullable(nurse);
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
