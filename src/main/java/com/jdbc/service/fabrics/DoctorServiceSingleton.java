package com.jdbc.service.fabrics;


import com.jdbc.dao.fabric.DoctorDaoSingleton;
import com.jdbc.service.DoctorServiceImpl;
import com.jdbc.service.api.DoctorService;

import java.beans.PropertyVetoException;

public class DoctorServiceSingleton {
    private volatile static DoctorService instance;

    private DoctorServiceSingleton() {
    }

    public static DoctorService getInstance() throws PropertyVetoException {
        if (instance == null) {
            synchronized (DoctorServiceSingleton.class) {
                if (instance == null) {
                    instance = new DoctorServiceImpl(DoctorDaoSingleton.getInstance()) {
                    };
                }
            }
        }
        return instance;
    }
}
