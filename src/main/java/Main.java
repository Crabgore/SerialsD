import database.DBConnector;
import database.DBObjects;
import database.SerialDB;
import database.SerialMapper;
import model.Serial;

import java.sql.Connection;

public class Main {
    public static final SerialDB serialMapper = new SerialMapper();

    public static void main(String[] args) {
        Connection connection = DBConnector.getConnection();

        DBObjects.createObject(connection);

        addSerials(connection);
        showSerials(connection);

        DBObjects.dropObject(connection);
        DBConnector.close();
    }

    private static void addSerials(Connection connection) {
        int count = 1;
        for (int i = 0; i < 5; i++) {
            Serial serial = new Serial(count, "Название сериала " + count, 3, 24, 2019);
            serialMapper.insert(connection, serial);
            count++;
        }
    }

    private static void showSerials(Connection connection) {
        serialMapper.selectAll(connection);
    }
}
