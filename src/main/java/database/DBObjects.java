package database;

import model.Sequence;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBObjects {
    public static void createObject (Connection connection) {
        createSequence(connection);
        createTable(connection);
    }

    private static void createSequence(Connection connection) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String sql = String.format("CREATE SEQUENCE %s START %d;", Sequence.NAME, Sequence.START);
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createTable(Connection connection) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS serials (id integer primary key, title text, seasons integer, series integer, year integer);";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void dropObject (Connection connection) {
        dropSequence(connection);
        dropTable(connection);
    }

    private static void dropSequence(Connection connection) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String sql = String.format("DROP SEQUENCE IF EXISTS %s;", Sequence.NAME);
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void dropTable(Connection connection) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            String sql = "DROP TABLE IF EXISTS serials;";
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
