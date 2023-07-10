package com.jdbc.dao.fabric;

import com.jdbc.dao.PatientDaoImpl;
import com.jdbc.dao.api.PatientDao;
import com.jdbc.orm.fabrics.SessionFactorySingleton;

import java.beans.PropertyVetoException;

public class PatientDaoSingleton {
    private volatile static PatientDaoImpl instance;
    private PatientDaoSingleton() {
    }
    public static PatientDao getInstance() throws PropertyVetoException {
        if (instance == null) {
            synchronized (PatientDaoSingleton.class) {
                if (instance == null) {
                    instance = new PatientDaoImpl(SessionFactorySingleton.getInstance());
                }
            }
        }
        return instance;
    }
}

