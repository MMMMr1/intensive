package com.jdbc.dao.api;

import org.hibernate.Session;

public interface Transactional {
    void setSession(Session session);
}
