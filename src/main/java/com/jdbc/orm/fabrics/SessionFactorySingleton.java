package com.jdbc.orm.fabrics;


import com.jdbc.orm.SessionManager;

public class SessionFactorySingleton {

    private volatile static SessionManager instance;

    private SessionFactorySingleton(){

    }


    public static SessionManager getInstance() {
        if (instance == null) {
            synchronized (SessionFactorySingleton.class) {
                if (instance == null) {
                    instance = new SessionManager( );
                }
            }
        }
        return instance;
    }
}
