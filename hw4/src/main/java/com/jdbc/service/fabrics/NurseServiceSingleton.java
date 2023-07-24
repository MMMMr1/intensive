package com.jdbc.service.fabrics;


import com.jdbc.dao.fabric.NurseDaoSingleton;
import com.jdbc.service.NurseServiceImpl;
import com.jdbc.service.api.NurseService;

import java.beans.PropertyVetoException;

public class NurseServiceSingleton {
    private volatile static NurseService instance;

    private NurseServiceSingleton() {
    }

    public static NurseService getInstance() throws PropertyVetoException {
        if (instance == null) {
            synchronized (NurseServiceSingleton.class) {
                if (instance == null) {
                    instance = new NurseServiceImpl(NurseDaoSingleton.getInstance()) {
                    };
                }
            }
        }
        return instance;
    }
}
