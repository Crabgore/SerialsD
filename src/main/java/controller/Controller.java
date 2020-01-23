package controller;

import model.database.DBConnector;
import model.database.DBObjects;
import model.database.SerialDB;
import model.database.SerialMapper;
import model.Serial;
import view.Main;

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
            Main.added(serial.getTitle());
            count++;
        }
    }

    public void  deleteSerial(int id) {
        String title = serialMapper.findById(connection, id).getTitle();
        serialMapper.delete(connection, id);
        Main.deleted(title);
    }

    public void showSerials() {
        Main.showSerials(serialMapper.selectAll(connection));
    }

    public void end() {
        DBConnector.close();
    }
}
