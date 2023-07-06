package com.jdbc.dao.api;

import java.sql.Connection;

public interface Transactional {
    void setConnection(Connection connection);
}
