package com.todo.todolist.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;
    private String url = "jdbc:h2:~/todolist";  // URL de la base de datos H2

    private DatabaseConnection() throws SQLException {
        try {
            this.connection = DriverManager.getConnection(url, "sa", "");
        } catch (SQLException ex) {
            throw new SQLException("Error al conectar con la base de datos.");
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}
