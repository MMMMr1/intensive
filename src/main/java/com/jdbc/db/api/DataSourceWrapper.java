package com.jdbc.db.api;

import java.sql.Connection;
import java.sql.SQLException;

public interface DataSourceWrapper extends AutoCloseable{
    Connection getConnection() throws SQLException;
}
