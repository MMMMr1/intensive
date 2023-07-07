package com.jdbc.dao;

import com.jdbc.dao.api.PatientDao;
import com.jdbc.db.api.DataSourceWrapper;
import com.jdbc.entity.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PatientDaoImpl implements PatientDao {
    private final String SQL_CREATE = "INSERT INTO app.patients (uuid, lastname, firstname, surname, address, phone, medical_card_number, dt_created, dt_updated) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private final String SQL_UPDATE = "UPDATE  app.patients SET lastname = ?, firstname = ?, surname = ?, address = ?, phone = ?, medical_card_number = ?, dt_updated  = ? WHERE uuid = ?;";
    private final String SQL_DELETE = "DELETE FROM app.patients  WHERE uuid = ?;";
    private final String SQL_GET = "SELECT uuid, lastname, firstname, surname, address, phone, medical_card_number, dt_created, dt_updated FROM app.patients;";
    private final String SQL_GET_BY_ID = "SELECT uuid, lastname, firstname, surname, address, phone, medical_card_number, dt_created, dt_updated FROM app.patients WHERE uuid = ?;";
    private DataSourceWrapper dataSource;
    private Connection connection;

    public PatientDaoImpl(DataSourceWrapper instance) {
        this.dataSource = instance;
    }

    @Override
    public void create(Patient patient) {
        try {
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE);
            preparedStatement.setObject(1, patient.getId());
            preparedStatement.setString(2, patient.getLastName());
            preparedStatement.setString(3, patient.getFirstName());
            preparedStatement.setString(4, patient.getSurName());
            preparedStatement.setString(5, patient.getAddress());
            preparedStatement.setString(6, patient.getPhone());
            preparedStatement.setString(7, patient.getMedicalCardNumber());
            preparedStatement.setObject(8, patient.getDtCreated());
            preparedStatement.setObject(9, patient.getDtUpdated());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Database connection error", e);
        }
    }

    @Override
    public void update(UUID uuid, Patient patient) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
            preparedStatement.setString(1, patient.getLastName());
            preparedStatement.setString(2, patient.getFirstName());
            preparedStatement.setString(3, patient.getSurName());
            preparedStatement.setString(4, patient.getAddress());
            preparedStatement.setString(5, patient.getPhone());
            preparedStatement.setString(6, patient.getMedicalCardNumber());
            preparedStatement.setObject(7, patient.getDtUpdated());
            preparedStatement.setObject(8, uuid);
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
    public List<Patient> findAll() {
        List<Patient> list = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET,
                     ResultSet.TYPE_SCROLL_INSENSITIVE,
                     ResultSet.CONCUR_READ_ONLY)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UUID uuid = (UUID) resultSet.getObject("uuid");
                String lastname = resultSet.getString("lastname");
                String firstname = resultSet.getString("firstname");
                String surname = resultSet.getString("surname");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                String medicalCardNumber = resultSet.getString("medical_card_number");
                LocalDateTime dtCreated =  resultSet.getObject("dt_created", LocalDateTime.class);
                LocalDateTime dtUpdated =  resultSet.getObject("dt_updated", LocalDateTime.class);
                list.add(new Patient(uuid, lastname, firstname, surname, address, phone, medicalCardNumber, dtCreated, dtUpdated));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException("Database connection error ", e);
        }
    }

    @Override
    public Optional<Patient> findPatientById(UUID uuid) {
        Patient patient = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_GET_BY_ID, ResultSet.CONCUR_READ_ONLY)
        ) {
            statement.setObject(1, uuid);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String lastname = resultSet.getString("lastname");
                String firstname = resultSet.getString("firstname");
                String surname = resultSet.getString("surname");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                String medicalCardNumber = resultSet.getString("medical_card_number");
                LocalDateTime dtCreated =  resultSet.getObject("dt_created", LocalDateTime.class);
                LocalDateTime dtUpdated =  resultSet.getObject("dt_updated", LocalDateTime.class);
                patient = new Patient(uuid, lastname, firstname, surname, address, phone, medicalCardNumber, dtCreated, dtUpdated);
            }
            return Optional.ofNullable(patient);
        } catch (SQLException e) {
            throw new RuntimeException("Database connection error", e);
        }
    }

    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
