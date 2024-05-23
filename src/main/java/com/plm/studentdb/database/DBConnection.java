package com.plm.studentdb.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "Jeshaiah";
    private static final String PASSWORD = "JeshaiahMae9123";
    private static final String DATABASE_NAME = "studentdb";
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            try (Statement stmt = connection.createStatement()) {
                String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS " + DATABASE_NAME;
                stmt.executeUpdate(createDatabaseQuery);
            }

            connection = DriverManager.getConnection(URL + DATABASE_NAME, USER, PASSWORD);

            initializeTables();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // TODO Table Structure might change if added more columns
    private static void initializeTables() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            String createTableQuery = "CREATE TABLE IF NOT EXISTS studentdb.student_record (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "student_id INT, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "course VARCHAR(100) NOT NULL, " +
                    "year INT NOT NULL, " +
                    "final_grade DECIMAL(4, 2) NOT NULL, " +
                    "gwa DECIMAL(4, 2) NOT NULL, " +
                    "status VARCHAR(20) NOT NULL)";
            stmt.executeUpdate(createTableQuery);
        }
    }


    public static Connection getConnection() {
        return connection;
    }

    public static void main(String[] args) {
        if (DBConnection.getConnection() != null) {
            System.out.println("Connection to database established successfully!");
        } else {
            System.out.println("Failed to establish connection to database.");
        }
        DBAdd.addStudentRecord(202334102, "Raphael Catacutan", "BSCS", 1, 1.00, 1.00, "Regular");
        DBAdd.addStudentRecord(202334019, "Jeshaiah Mulleno", "BSCS", 1, 1.00, 1.00, "Regular");
        DBEdit.editStudentRecord();
    }
}
