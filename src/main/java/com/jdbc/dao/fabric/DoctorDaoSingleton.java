package com.jdbc.dao.fabric;

import com.jdbc.dao.DoctorDaoImpl;
import com.jdbc.dao.api.DoctorDao;
import com.jdbc.orm.fabrics.SessionFactorySingleton;

import java.beans.PropertyVetoException;

public class DoctorDaoSingleton {
    private volatile static DoctorDaoImpl instance;

    private DoctorDaoSingleton() {
    }

    public static DoctorDao getInstance() throws PropertyVetoException {
        if (instance == null) {
            synchronized (DoctorDaoSingleton.class) {
                if (instance == null) {
                    instance = new DoctorDaoImpl(SessionFactorySingleton.getInstance());
                }
            }
        }
        return instance;
    }
}

