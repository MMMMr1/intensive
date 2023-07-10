package com.jdbc.orm;


import com.jdbc.entity.Patient;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

@Log4j2
public class SessionManager {


    SessionFactory sessionFactory;
    public SessionManager( ) {
        Configuration configuration = new Configuration();
         configuration.addAnnotatedClass(Patient.class);

        configuration.configure();

        this.sessionFactory =  configuration.buildSessionFactory();
//        try {
//            Configuration configuration = new Configuration();
//            configuration.configure();
//            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
//            this.sessionFactory = configuration.buildSessionFactory(builder.build());
//
//        } catch (Throwable e){
//            log.error("Initial SessionFactory creation failed. "+ e);
//        }
    }
    public Session getSession() {
        return sessionFactory.openSession();
    }
}
