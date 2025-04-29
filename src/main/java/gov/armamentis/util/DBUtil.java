package gov.armamentis.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/armamentis";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "12345"; // XAMPP default: empty

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL 8+ Driver
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
    }
}