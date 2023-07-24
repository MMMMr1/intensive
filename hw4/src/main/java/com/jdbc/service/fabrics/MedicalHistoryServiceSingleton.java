package com.jdbc.service.fabrics;


import com.jdbc.dao.fabric.MedicalHistoryDaoSingleton;
import com.jdbc.service.MedicalHistoryServiceImpl;
import com.jdbc.service.api.MedicalHistoryService;

import java.beans.PropertyVetoException;

public class MedicalHistoryServiceSingleton {
    private volatile static MedicalHistoryService instance;

    private MedicalHistoryServiceSingleton() {
    }

    public static  MedicalHistoryService getInstance() throws PropertyVetoException {
        if (instance == null) {
            synchronized (MedicalHistoryServiceSingleton.class) {
                if (instance == null) {
                    instance = new  MedicalHistoryServiceImpl( MedicalHistoryDaoSingleton.getInstance(),
                            DoctorServiceSingleton.getInstance(),
                            PatientServiceSingleton.getInstance()) {
                    };
                }
            }
        }
        return instance;
    }
}
