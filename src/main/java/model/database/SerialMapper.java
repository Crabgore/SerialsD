package model.database;

import model.Sequence;
import model.Serial;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SerialMapper implements SerialDB{
    private Map<Integer, Serial> serialMap = new ConcurrentHashMap<>();

    @Override
    public void insert(Connection connection, Serial serial) {
        PreparedStatement preparedStatement = null;
        String insert = "INSERT into serials values (?, ?, ?, ?, ?);";
        try {
            serial.setId(Sequence.nextVal(connection));
            preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setInt(1, serial.getId());
            preparedStatement.setString(2, serial.getTitle());
            preparedStatement.setInt(3, serial.getSeasons());
            preparedStatement.setInt(4, serial.getSeries());
            preparedStatement.setInt(5, serial.getYear());
            preparedStatement.executeUpdate();
            serialMap.put(serial.getId(), serial);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Connection connection, Serial serial) {
        PreparedStatement preparedStatement = null;
        String insert = "UPDATE serials SET title = ?, seasons = ?, series = ?, year = ? WHERE id = ?;";
        try {
            preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, serial.getTitle());
            preparedStatement.setInt(2, serial.getSeasons());
            preparedStatement.setInt(3, serial.getSeries());
            preparedStatement.setInt(4, serial.getYear());
            preparedStatement.setInt(5, serial.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Connection connection, int id) {
        int result = -1;
        PreparedStatement preparedStatement = null;
        String insert = "DELETE FROM serials WHERE id = ?;";
        try {
            preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setInt(1, id);
            int count = preparedStatement.executeUpdate();
            result = 0;
            serialMap.remove(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Serial> selectAll(Connection connection) {
        Statement statement = null;
        List<Serial> serials = new ArrayList<>();
        String insert = "SELECT * FROM serials;";
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(insert);
            while (resultSet.next()) {
                Serial serial = new Serial(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
                        resultSet.getInt(4), resultSet.getInt(5));
                serials.add(serial);
                serialMap.put(serial.getId(), serial);
                System.out.println(serial.getTitle());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return serials;
    }

    @Override
    public Serial findById(Connection connection, int id) {
        if (serialMap.containsKey(id)) {
            return serialMap.get(id);
        }
        Serial serial = null;
        PreparedStatement preparedStatement = null;
        String insert = "SELECT * FROM serials WHERE id = ?;";
        try {
            preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                serial = new Serial(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
                        resultSet.getInt(4), resultSet.getInt(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return serial;
    }
}
