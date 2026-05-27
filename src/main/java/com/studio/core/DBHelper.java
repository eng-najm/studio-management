package com.studio.core;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class DBHelper {
    Dotenv dotenv = Dotenv.load();

    public Connection connection() throws SQLException {
        try {
            return DriverManager.getConnection(dotenv.get("DB_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PASSWORD"));
        } catch (SQLException e) {
            throw new SQLException("Error");
        }
    }

}
