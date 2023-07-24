package com.jdbc.orm;


import com.jdbc.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionManager {
    SessionFactory sessionFactory;

    public SessionManager() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Patient.class);
        configuration.addAnnotatedClass(Doctor.class);
        configuration.addAnnotatedClass(MedicalHistory.class);
        configuration.addAnnotatedClass(Nurse.class);
        configuration.addAnnotatedClass(Employee.class);
        configuration.configure();
        this.sessionFactory = configuration.buildSessionFactory();
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }
}
