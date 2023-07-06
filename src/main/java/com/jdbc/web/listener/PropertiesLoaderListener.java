package com.jdbc.web.listener;



import com.jdbc.db.api.DataSourceWrapper;
import com.jdbc.db.fabric.DataSourceSingleton;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
@WebListener
public class PropertiesLoaderListener  implements ServletContextListener {
        @Override
        public void contextInitialized(ServletContextEvent servletContextEvent) {
            File confDir = new File(System.getenv("catalina_base") + "/conf");
            File prop = new File(confDir + "/application.properties");
            try {
                Properties properties = new Properties();
                properties.load(new FileReader(prop));
                DataSourceSingleton.setProperties(properties);
            } catch (FileNotFoundException e) {
                throw new IllegalStateException("File with properties not found, create application.properties in conf", e);
            } catch (IOException e) {
                throw new RuntimeException("Exception in reading application.properties ", e);
            }
        }

        @Override
        public void contextDestroyed(ServletContextEvent servletContextEvent) {
            ServletContext servletContext = servletContextEvent.getServletContext();
            DataSourceWrapper DataSourceWrapper = (DataSourceWrapper) servletContext.getAttribute("DateSource");
            try {
                DataSourceWrapper.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            //deregister drivers
//            deregisterDrivers(getDrivers());
        }


//        Enumeration<Driver> getDrivers(){
//            return DriverManager.getDrivers();
//        }
//        void deregisterDrivers(Enumeration<Driver> drivers){
//            while (drivers.hasMoreElements()){
//                deregisterDriver(drivers.nextElement());
//            }
//        }
//        void deregisterDriver(Driver driver){
//            try {
//                DriverManager.deregisterDriver(driver);
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
}
