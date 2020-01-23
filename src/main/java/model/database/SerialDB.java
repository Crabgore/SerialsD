package model.database;

import model.Serial;

import java.sql.Connection;
import java.util.List;

public interface SerialDB {
    void insert(Connection connection, Serial serial);
    void update(Connection connection, Serial serial);
    void delete(Connection connection, int id);

    List<Serial> selectAll(Connection connection);
    Serial findById(Connection connection, int id);
}
