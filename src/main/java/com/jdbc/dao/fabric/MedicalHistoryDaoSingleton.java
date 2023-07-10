package com.jdbc.dao.fabric;

import com.jdbc.dao.MedicalHistoryDaoImpl;
import com.jdbc.dao.api.MedicalHistoryDao;
import com.jdbc.orm.fabrics.SessionFactorySingleton;

import java.beans.PropertyVetoException;

public class MedicalHistoryDaoSingleton {
    private volatile static MedicalHistoryDaoImpl instance;

    private MedicalHistoryDaoSingleton() {
    }

    public static MedicalHistoryDao getInstance() throws PropertyVetoException {
        if (instance == null) {
            synchronized (MedicalHistoryDaoSingleton.class) {
                if (instance == null) {
                    instance = new MedicalHistoryDaoImpl(SessionFactorySingleton.getInstance());
                }
            }
        }
        return instance;
    }
}

