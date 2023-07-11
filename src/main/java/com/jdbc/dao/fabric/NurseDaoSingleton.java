package com.jdbc.dao.fabric;

import com.jdbc.dao.NurseDaoImpl;
import com.jdbc.dao.api.NurseDao;
import com.jdbc.orm.fabrics.SessionFactorySingleton;

import java.beans.PropertyVetoException;

public class NurseDaoSingleton {
    private volatile static NurseDaoImpl instance;

    private NurseDaoSingleton() {
    }
    public static NurseDao getInstance() throws PropertyVetoException {
        if (instance == null) {
            synchronized (NurseDaoSingleton.class) {
                if (instance == null) {
                    instance = new NurseDaoImpl(SessionFactorySingleton.getInstance());
                }
            }
        }
        return instance;
    }
}

