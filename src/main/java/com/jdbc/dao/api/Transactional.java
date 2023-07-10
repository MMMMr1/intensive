package com.jdbc.dao.api;

import org.hibernate.Session;

import java.sql.Connection;

public interface Transactional {
    void setSession(Session session);
}
