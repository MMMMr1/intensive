package com.jdbc.dao;

import com.jdbc.dao.api.DoctorDao;
import com.jdbc.db.api.DataSourceWrapper;
import com.jdbc.entity.Doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class DoctorDaoImpl implements DoctorDao {
    private final String SQL_CREATE = "INSERT INTO app.doctors (uuid, lastname, firstname, surname, position, department, dt_created, dt_updated) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    private final String SQL_UPDATE = "UPDATE  app.doctors SET lastname = ?, firstname = ?, surname = ?, position = ?, department = ?,  dt_updated  = ? WHERE uuid = ?;";
    private final String SQL_DELETE = "DELETE FROM app.doctors  WHERE uuid = ?;";
    private final String SQL_GET = "SELECT uuid, lastname, firstname, surname, position, department, dt_created, dt_updated FROM app.doctors;";
    private final String SQL_GET_BY_ID = "SELECT uuid, lastname, firstname, surname, position, department, dt_created, dt_updated FROM app.doctors WHERE id = ?;";
    private DataSourceWrapper dataSource;
    private Connection connection;

    public DoctorDaoImpl(DataSourceWrapper instance) {
        this.dataSource = instance;
    }

    @Override
    public void create(Doctor doctor) {
        try {
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE);
            preparedStatement.setObject(1, doctor.getId());
            preparedStatement.setString(2, doctor.getLastName());
            preparedStatement.setString(3, doctor.getFirstName());
            preparedStatement.setString(4, doctor.getSurName());
            preparedStatement.setString(5, doctor.getPosition());
            preparedStatement.setString(6, doctor.getDepartment());
            preparedStatement.setObject(7, doctor.getDtCreated());
            preparedStatement.setObject(8, doctor.getDtUpdated());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Database connection error", e);
        }
    }

    @Override
    public void update(UUID uuid, Doctor doctor) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, doctor.getLastName());
            preparedStatement.setString(2, doctor.getFirstName());
            preparedStatement.setString(3, doctor.getSurName());
            preparedStatement.setString(4, doctor.getPosition());
            preparedStatement.setString(5, doctor.getDepartment());
            preparedStatement.setObject(6, doctor.getDtUpdated());
            preparedStatement.setObject(7, uuid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Database connection error", e);
        }
    }

    @Override
    public void delete(UUID uuid) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
            preparedStatement.setObject(1, uuid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Database connection error", e);
        }
    }

    @Override
    public List<Doctor> findAll() {
        List<Doctor> list = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UUID uuid = (UUID) resultSet.getObject("uuid");
                String lastname = resultSet.getString("lastname");
                String firstname = resultSet.getString("firstname");
                String surname = resultSet.getString("surname");
                String position = resultSet.getString("position");
                String department = resultSet.getString("department");
                LocalDateTime dtCreated = (LocalDateTime) resultSet.getObject("dt_created", LocalDateTime.class);
                LocalDateTime dtUpdated = (LocalDateTime) resultSet.getObject("dt_updated", LocalDateTime.class);
                list.add(new Doctor(uuid, lastname, firstname, surname, position, department, dtCreated, dtUpdated));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Database connection error", e);
        }
    }

    @Override
    public Optional<Doctor> findDoctorById(UUID uuid) {
        Doctor doctor = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_GET_BY_ID)
        ) {
            statement.setObject(1, uuid);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String lastname = resultSet.getString("lastname");
                String firstname = resultSet.getString("firstname");
                String surname = resultSet.getString("surname");
                String position = resultSet.getString("position");
                String department = resultSet.getString("department");
                LocalDateTime dtCreated = (LocalDateTime) resultSet.getObject("dt_created", LocalDateTime.class);
                LocalDateTime dtUpdated = (LocalDateTime) resultSet.getObject("dt_updated", LocalDateTime.class);
                doctor = new Doctor(uuid, lastname, firstname, surname, position, department, dtCreated, dtUpdated);
            }
            return Optional.ofNullable(doctor);
        } catch (SQLException e) {
            throw new RuntimeException("Database connection error", e);
        }
    }

    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
