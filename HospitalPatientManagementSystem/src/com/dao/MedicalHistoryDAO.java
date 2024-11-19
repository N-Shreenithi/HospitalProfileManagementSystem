package com.dao;

import com.model.MedicalHistory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicalHistoryDAO {

    // Method to add a new medical history record
    public boolean addMedicalHistory(MedicalHistory medicalHistory) {
        String query = "INSERT INTO MedicalHistory (patient_id, condition, treatment, treatment_start_date, treatment_end_date) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, medicalHistory.getPatientId());
            stmt.setString(2, medicalHistory.getMedicalCondition());
            stmt.setString(3, medicalHistory.getTreatment());
            stmt.setDate(4, new java.sql.Date(medicalHistory.getTreatmentStartDate().getTime()));
            stmt.setDate(5, medicalHistory.getTreatmentEndDate() != null ? new java.sql.Date(medicalHistory.getTreatmentEndDate().getTime()) : null);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to update an existing medical history record
    public boolean updateMedicalHistory(MedicalHistory medicalHistory) {
        String query = "UPDATE MedicalHistory SET patient_id = ?, condition = ?, treatment = ?, treatment_start_date = ?, treatment_end_date = ? WHERE history_id = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, medicalHistory.getPatientId());
            stmt.setString(2, medicalHistory.getMedicalCondition());
            stmt.setString(3, medicalHistory.getTreatment());
            stmt.setDate(4, new java.sql.Date(medicalHistory.getTreatmentStartDate().getTime()));
            stmt.setDate(5, medicalHistory.getTreatmentEndDate() != null ? new java.sql.Date(medicalHistory.getTreatmentEndDate().getTime()) : null);
            stmt.setInt(6, medicalHistory.getHistoryId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to get a medical history record by its ID
    public MedicalHistory getMedicalHistoryById(int historyId) {
        String query = "SELECT * FROM MedicalHistory WHERE history_id = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, historyId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int patientId = rs.getInt("patient_id");
                    String condition = rs.getString("condition");
                    String treatment = rs.getString("treatment");
                    Date treatmentStartDate = rs.getDate("treatment_start_date");
                    Date treatmentEndDate = rs.getDate("treatment_end_date");

                    return new MedicalHistory(historyId, patientId, condition, treatment, treatmentStartDate, treatmentEndDate);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if no medical history found
    }

    // Method to get all medical history records
    public List<MedicalHistory> getAllMedicalHistories() {
        List<MedicalHistory> medicalHistories = new ArrayList<>();
        String query = "SELECT * FROM MedicalHistory";

        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int historyId = rs.getInt("history_id");
                int patientId = rs.getInt("patient_id");
                String condition = rs.getString("condition");
                String treatment = rs.getString("treatment");
                Date treatmentStartDate = rs.getDate("treatment_start_date");
                Date treatmentEndDate = rs.getDate("treatment_end_date");

                MedicalHistory medicalHistory = new MedicalHistory(historyId, patientId, condition, treatment, treatmentStartDate, treatmentEndDate);
                medicalHistories.add(medicalHistory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return medicalHistories;
    }

    // Method to delete a medical history record
    public boolean deleteMedicalHistory(int historyId) {
        String query = "DELETE FROM MedicalHistory WHERE history_id = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, historyId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
 // Method to retrieve medical history by patient ID
    public List<MedicalHistory> getMedicalHistoryByPatientId(int patientId) {
        List<MedicalHistory> medicalHistoryList = new ArrayList<>();

        String query = "SELECT * FROM MedicalHistory WHERE patient_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
        		PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, patientId);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    // Create a MedicalHistory object for each record
                    int historyId = resultSet.getInt("history_id");
                    String condition = resultSet.getString("medical_condition");
                    String treatment = resultSet.getString("treatment");
                    Date treatmentStartDate = resultSet.getDate("treatment_start_date");
                    Date treatmentEndDate = resultSet.getDate("treatment_end_date");

                    MedicalHistory medicalHistory = new MedicalHistory(historyId, patientId, condition, treatment, treatmentStartDate, treatmentEndDate);
                    medicalHistoryList.add(medicalHistory); // Add each record to the list
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception based on your application's requirements
        }
        
        return medicalHistoryList;
    }
}

