package com.jdbc.dao;


import com.jdbc.dao.api.Transactional;
import com.jdbc.db.fabric.DataSourceSingleton;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

public class EntityTransaction {
    private Connection connection;
    Logger log = Logger.getLogger(EntityTransaction.class.getName());
    public void initTransaction(Transactional dao, Transactional... daos){
        if (connection == null){
            try {
                connection = DataSourceSingleton.getInstance().getConnection();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (PropertyVetoException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dao.setConnection(connection);
        for (Transactional daoElements : daos){
            daoElements.setConnection(connection);
        }
    }
    public void endTransaction(){
        if (connection == null){
            throw new RuntimeException("Connection is null");
        }
        try {
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            log.info("Impossible setAutoCommit to true ");
        }
        try {
            connection.close();
        } catch (SQLException e) {
            log.info("Impossible close connection " + e.getMessage());
        }
    }
    public void commit(){
        try {
            connection.commit();
        } catch (SQLException e) {
            log.info("Impossible commit "+ e.getMessage());
        }
    }
    public void rollback(){
        try {
            connection.rollback();
        } catch (SQLException e) {
            log.info("Impossible rollback  "+ e.getMessage());

        }
    }
}
