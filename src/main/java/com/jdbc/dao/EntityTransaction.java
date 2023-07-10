package com.jdbc.dao;


import com.jdbc.dao.api.Transactional;
import com.jdbc.orm.SessionManager;
import com.jdbc.orm.fabrics.SessionFactorySingleton;
import org.hibernate.Session;

import java.util.logging.Logger;

public class EntityTransaction {
    private SessionManager sessionManager;
    private Session session;
    Logger log = Logger.getLogger(EntityTransaction.class.getName());
    public void initTransaction(Transactional dao, Transactional... daos){
        if (session == null){
            try {
                session = SessionFactorySingleton.getInstance().getSession();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        try {
            session.beginTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dao.setSession(session);
        for (Transactional daoElements : daos){
            daoElements.setSession(session);
        }
    }
    public void endTransaction(){
        if (session == null){
            throw new RuntimeException("Connection is null");
        }

        try {
            session.close();
        } catch (Exception e) {
            log.info("Impossible close connection " + e.getMessage());
        }
    }
    public void commit(){
        try {
            session.getTransaction().commit();
        } catch (Exception e) {
            log.info("Impossible commit "+ e.getMessage());
        }
    }
    public void rollback(){
        try {
            session.getTransaction().rollback();
        } catch (Exception e) {
            log.info("Impossible rollback  "+ e.getMessage());

        }
    }
}
