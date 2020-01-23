package controller;

import model.database.DBConnector;
import model.database.DBObjects;
import model.database.SerialDB;
import model.database.SerialMapper;
import model.Serial;

import java.sql.Connection;

public class Controller {
    public final SerialDB serialMapper;
    private Connection connection;

    public Controller() {
        serialMapper = new SerialMapper();
        connection = DBConnector.getConnection();
        DBObjects.dropObject(connection);
        DBObjects.createObject(connection);
    }

    public void addSerials() {
        int count = 1;
        for (int i = 0; i < 5; i++) {
            Serial serial = new Serial(count, "Название сериала " + count, 3, 24, 2019);
            serialMapper.insert(connection, serial);
            count++;
        }
    }

    public void  deleteSerial(int id) {
        serialMapper.delete(connection, id);
    }

    public void showSerials() {
        serialMapper.selectAll(connection);
    }

    public void end() {
        DBConnector.close();
    }
}
