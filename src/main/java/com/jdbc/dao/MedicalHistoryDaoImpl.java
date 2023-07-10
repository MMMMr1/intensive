package com.jdbc.dao;

import com.jdbc.dao.api.MedicalHistoryDao;
import com.jdbc.entity.MedicalHistory;
import com.jdbc.orm.SessionManager;
import org.hibernate.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class MedicalHistoryDaoImpl implements MedicalHistoryDao {
    private final String SQL_CREATE = "INSERT INTO app.medical_histories (uuid, diagnosis, treatment, dt_created, dt_updated, id_patient, id_doctor) VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING uuid;";
    private final String SQL_UPDATE = "UPDATE  app.medical_histories SET diagnosis = ?, treatment = ?, id_patient = ?, id_doctor = ?, dt_updated  = ? WHERE uuid = ?;";
    private final String SQL_DELETE = "DELETE FROM app.medical_histories  WHERE uuid = ?;";
    private final String SQL_GET = "SELECT uuid, diagnosis, treatment, dt_created, dt_updated, id_patient, id_doctor FROM app.medical_histories;";
    private final String SQL_GET_BY_ID = "SELECT uuid, diagnosis, treatment, dt_created, dt_updated, id_patient, id_doctor FROM app.medical_histories WHERE uuid = ?;";
    private SessionManager session;
    private Connection connection;

    public MedicalHistoryDaoImpl(SessionManager session) {
        this.session = session;
    }
    @Override
    public UUID create(MedicalHistory history) {
        UUID uuid = null;
//        try {
//             PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE);
//            preparedStatement.setObject(1, history.getUuid());
//            preparedStatement.setString(2, history.getDiagnosis());
//            preparedStatement.setString(3, history.getTreatment());
//            preparedStatement.setObject(4, history.getDtCreated());
//            preparedStatement.setObject(5, history.getDtUpdated());
//            preparedStatement.setObject(6, history.getPatient());
//            preparedStatement.setObject(7, history.getDoctor());
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()) {
//                uuid = (UUID) resultSet.getObject("uuid");
//            }
            return uuid;
//        } catch (SQLException e) {
//            throw new RuntimeException("Database connection error", e);
//        }
    }

    @Override
    public void update(UUID uuid, MedicalHistory history) {
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE);
//            preparedStatement.setString(1, history.getDiagnosis());
//            preparedStatement.setString(2, history.getTreatment());
//            preparedStatement.setObject(3, history.getPatient());
//            preparedStatement.setObject(4, history.getDoctor());
//            preparedStatement.setObject(5, history.getDtUpdated());
//            preparedStatement.setObject(6, history.getUuid());
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException("Database connection error", e);
//        }
    }

    @Override
    public void delete(UUID uuid) {
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE);
//            preparedStatement.setObject(1, uuid);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new RuntimeException("Database connection error", e);
//        }
    }

    @Override
    public List<MedicalHistory> findAll() {
        List<MedicalHistory> list = new ArrayList<>();
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET,
//                     ResultSet.TYPE_SCROLL_INSENSITIVE,
//                     ResultSet.CONCUR_READ_ONLY)) {
//            ResultSet resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                UUID uuid = (UUID) resultSet.getObject("uuid");
//                String diagnosis = resultSet.getString("diagnosis");
//                String treatment = resultSet.getString("treatment");
//                LocalDateTime dtCreated =  resultSet.getObject("dt_created", LocalDateTime.class);
//                LocalDateTime dtUpdated =  resultSet.getObject("dt_updated", LocalDateTime.class);
//                UUID id_patient =  (UUID) resultSet.getObject("id_patient");
//                UUID id_doctor =  (UUID) resultSet.getObject("id_doctor");
//                list.add(new MedicalHistory( uuid, id_patient, id_doctor, diagnosis, treatment, dtCreated, dtUpdated));
//            }
            return list;
//        } catch (SQLException e) {
//            throw new RuntimeException("Database connection error ", e);
//        }
    }

    @Override
    public Optional<MedicalHistory> findMedicalHistoryById(UUID uuid) {
        MedicalHistory medicalHistory = null;
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement statement = connection.prepareStatement(SQL_GET_BY_ID, ResultSet.CONCUR_READ_ONLY)
//        ) {
//            statement.setObject(1, uuid);
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                UUID id = (UUID) resultSet.getObject("uuid");
//                String diagnosis = resultSet.getString("diagnosis");
//                String treatment = resultSet.getString("treatment");
//                LocalDateTime dtCreated =  resultSet.getObject("dt_created", LocalDateTime.class);
//                LocalDateTime dtUpdated =  resultSet.getObject("dt_updated", LocalDateTime.class);
//                UUID id_patient =  (UUID) resultSet.getObject("id_patient");
//                UUID id_doctor =  (UUID) resultSet.getObject("id_doctor");
//                medicalHistory = new MedicalHistory(id, id_patient, id_doctor, diagnosis, treatment, dtCreated, dtUpdated);
//            }
            return Optional.ofNullable(medicalHistory);
//        } catch (SQLException e) {
//            throw new RuntimeException("Database connection error", e);
//        }
    }

    @Override
    public void setSession(Session session) {
//        this.session = session;
    }}
