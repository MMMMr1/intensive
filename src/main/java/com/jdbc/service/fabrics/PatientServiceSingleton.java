package com.jdbc.service.fabrics;


import com.jdbc.dao.fabric.PatientDaoSingleton;
import com.jdbc.service.PatientServiceImpl;
import com.jdbc.service.api.PatientService;

import java.beans.PropertyVetoException;

public class PatientServiceSingleton {
    private volatile static PatientService instance;

    private PatientServiceSingleton() {
    }

    public static PatientService getInstance() throws PropertyVetoException {
        if (instance == null) {
            synchronized (PatientServiceSingleton.class) {
                if (instance == null) {
                    instance = new PatientServiceImpl(PatientDaoSingleton.getInstance()) {
                    };
                }
            }
        }
        return instance;
    }
}
