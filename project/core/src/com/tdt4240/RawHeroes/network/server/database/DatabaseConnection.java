package com.tdt4240.RawHeroes.network.server.database;

import java.sql.*;

/**
 * Created by espen1 on 04.03.2015.
 */
public class DatabaseConnection {

   // private final static String connectionString = "jdbc:mysql://localhost:3306/databaseonly";
    private final static String connectionString = "jdbc:mysql://localhost:3306/test";
    private final static String userName = "root";
    private final static String password = "ayivcdt4";

    public static Connection getConnection() throws Exception {
        try {
            return DriverManager.getConnection(connectionString, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        throw new Exception("Failed to connect to database!");
    }
}
