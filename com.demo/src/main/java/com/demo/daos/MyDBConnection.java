package com.demo.daos;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyDBConnection {
    private static final Properties props = new Properties();
    private static Connection con = null;

    static {
        try {
            // Load the db.properties from classpath
            InputStream is = MyDBConnection.class.getClassLoader().getResourceAsStream("db.properties");
            if (is == null) {
                throw new IllegalArgumentException("❌ db.properties file not found in classpath");
            }

            props.load(is);

            String driver = props.getProperty("driver-class");
            String url = props.getProperty("driver-url");
            String user = props.getProperty("user");
            String pass = props.getProperty("password");

            // Load driver class
            Class.forName(driver);

            // Create DB connection
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("✅ Database connected successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return con;
    }
}
