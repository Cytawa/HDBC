package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    final String url= "jdbc:mysql://localhost:3306/shop?serverTime=CET";
    final String user="root";
    final String password = "root";

    private DatabaseConnection instance;
    private Connection connection;

    public DatabaseConnection() {
        try {
            this.connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
