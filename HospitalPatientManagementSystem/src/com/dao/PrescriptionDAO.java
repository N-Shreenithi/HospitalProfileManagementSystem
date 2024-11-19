package com.dao;

import com.model.Prescription;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionDAO {

    // Create a new prescription record
    public boolean addPrescription(Prescription prescription) {
        String query = "INSERT INTO Prescriptions (patient_id, doctor_id, medication_name, dosage, start_date, end_date) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, prescription.getPatientId());
            stmt.setInt(2, prescription.getDoctorId());
            stmt.setString(3, prescription.getMedicationName());
            stmt.setString(4, prescription.getDosage());
            stmt.setDate(5, new java.sql.Date(prescription.getStartDate().getTime()));
            stmt.setDate(6, new java.sql.Date(prescription.getEndDate().getTime()));

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve a prescription by ID
    public Prescription getPrescriptionById(int prescriptionId) {
        String query = "SELECT * FROM Prescriptions WHERE prescription_id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, prescriptionId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int patientId = rs.getInt("patient_id");
                int doctorId = rs.getInt("doctor_id");
                String medicationName = rs.getString("medication_name");
                String dosage = rs.getString("dosage");
                Date startDate = rs.getDate("start_date");
                Date endDate = rs.getDate("end_date");

                return new Prescription(prescriptionId, patientId, doctorId, medicationName, dosage, startDate, endDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Retrieve all prescriptions
    public List<Prescription> getAllPrescriptions() {
        List<Prescription> prescriptions = new ArrayList<>();
        String query = "SELECT * FROM Prescriptions";
        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int prescriptionId = rs.getInt("prescription_id");
                int patientId = rs.getInt("patient_id");
                int doctorId = rs.getInt("doctor_id");
                String medicationName = rs.getString("medication_name");
                String dosage = rs.getString("dosage");
                Date startDate = rs.getDate("start_date");
                Date endDate = rs.getDate("end_date");

                prescriptions.add(new Prescription(prescriptionId, patientId, doctorId, medicationName, dosage, startDate, endDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prescriptions;
    }

    // Update a prescription record
    public boolean updatePrescription(Prescription prescription) {
        String query = "UPDATE Prescriptions SET patient_id = ?, doctor_id = ?, medication_name = ?, dosage = ?, start_date = ?, end_date = ? WHERE prescription_id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, prescription.getPatientId());
            stmt.setInt(2, prescription.getDoctorId());
            stmt.setString(3, prescription.getMedicationName());
            stmt.setString(4, prescription.getDosage());
            stmt.setDate(5, new java.sql.Date(prescription.getStartDate().getTime()));
            stmt.setDate(6, new java.sql.Date(prescription.getEndDate().getTime()));
            stmt.setInt(7, prescription.getPrescriptionId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a prescription record
    public boolean deletePrescription(int prescriptionId) {
        String query = "DELETE FROM Prescriptions WHERE prescription_id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, prescriptionId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
