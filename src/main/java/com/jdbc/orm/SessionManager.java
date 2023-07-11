package com.jdbc.orm;


import com.jdbc.entity.*;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

@Log4j2
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
