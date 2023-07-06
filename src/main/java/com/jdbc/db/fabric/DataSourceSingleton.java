package com.jdbc.db.fabric;

import com.jdbc.db.DataSourceC3PO;
import com.jdbc.db.api.DataSourceWrapper;

import java.beans.PropertyVetoException;
import java.util.Properties;

public class DataSourceSingleton {
    private static Properties properties;
    private volatile static DataSourceWrapper instance;
    private DataSourceSingleton(){

    }
    public static void setProperties(Properties properties) {
        synchronized (DataSourceSingleton.class) {
            if (instance != null) {
                throw new IllegalStateException("Impossible to change properties. Connection has been already created");
            }
            DataSourceSingleton.properties = properties;
        }
    }

    public static DataSourceWrapper getInstance() throws PropertyVetoException {
        if (instance == null) {
            synchronized (DataSourceSingleton.class) {
                if (instance == null) {
                    instance = new DataSourceC3PO(properties);
                }
            }
        }
        return instance;
    }
}
