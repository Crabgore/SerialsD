package model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static final String URL = "jdbc:h2:~/test1";
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            DBConnector.registerDriver();
            try {
                connection = DriverManager.getConnection(URL, "user", "");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return connection;
    }

    private static void registerDriver() {
        try {
            DriverManager.registerDriver(new org.h2.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void close() {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
