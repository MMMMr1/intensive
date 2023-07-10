package com.jdbc.dao.fabric;

import com.jdbc.dao.DoctorDaoImpl;
import com.jdbc.dao.api.DoctorDao;
import com.jdbc.db.fabric.DataSourceSingleton;

import java.beans.PropertyVetoException;

public class DoctorDaoSingleton {
    private volatile static DoctorDaoImpl instance;

    private DoctorDaoSingleton() {
    }

    public static DoctorDao getInstance() throws PropertyVetoException {
        if (instance == null) {
            synchronized (DoctorDaoSingleton.class) {
                if (instance == null) {
                    instance = new DoctorDaoImpl(DataSourceSingleton.getInstance());
                }
            }
        }
        return instance;
    }
}

