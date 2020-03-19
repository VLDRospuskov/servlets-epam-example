package com.epam.ejcservlet.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfiguration {

    public static Connection getPostgreSQLConnection() throws ClassNotFoundException, SQLException {
        String hostName = "localhost";
        String dbName = "servletDB";
        String userName = "postgres";
        String password = "postgres";
        return getPostgreSQLConnection(hostName, dbName, userName, password);
    }

    public static Connection getPostgreSQLConnection(
            String hostName, String dbName, String userName, String password
    )
            throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String connectionURL = "jdbc:postgresql://" + hostName + ":5432/" + dbName;
        Connection conn = DriverManager.getConnection(connectionURL, userName, password);
        return conn;
    }

}
