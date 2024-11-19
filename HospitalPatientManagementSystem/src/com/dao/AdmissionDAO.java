package com.dao;

import com.model.Admission;

import java.sql.*;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class AdmissionDAO {

    // Method to add a new admission record
    public boolean addAdmission(Admission admission) {
        String query = "INSERT INTO Admissions (patient_id, doctor_id, admission_date, discharge_date, room_number, reason_for_admission, discharge_status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, admission.getPatientId());
            stmt.setInt(2, admission.getDoctorId());
            stmt.setDate(3, new java.sql.Date(admission.getAdmissionDate().getTime()));
            stmt.setDate(4, new java.sql.Date(admission.getDischargeDate().getTime()));
            stmt.setString(5, admission.getRoomNumber());
            stmt.setString(6, admission.getReasonForAdmission());
            stmt.setBoolean(7, admission.isDischargeStatus());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to update an existing admission record
    public boolean updateAdmission(Admission admission) {
        String query = "UPDATE Admissions SET doctor_id = ?, admission_date = ?, discharge_date = ?, room_number = ?, reason_for_admission = ?, discharge_status = ? WHERE admission_id = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, admission.getDoctorId());
            stmt.setDate(2, new java.sql.Date(admission.getAdmissionDate().getTime()));
            if (admission.getDischargeDate() != null) {
                stmt.setDate(3, new java.sql.Date(admission.getDischargeDate().getTime()));
            } else {
                stmt.setNull(3, Types.TIMESTAMP);
            }
            stmt.setString(4, admission.getRoomNumber());
            stmt.setString(5, admission.getReasonForAdmission());
            stmt.setBoolean(6, admission.isDischargeStatus());
            stmt.setInt(7, admission.getAdmissionId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to get an admission by its ID
    public Admission getAdmissionById(int admissionId) {
        String query = "SELECT * FROM Admissions WHERE admission_id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, admissionId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int patientId = rs.getInt("patient_id");
                    int doctorId = rs.getInt("doctor_id");
                    Date admissionDate = rs.getDate("admission_date");
                    Date dischargeDate = rs.getDate("discharge_date");
                    String roomNumber = rs.getString("room_number");
                    String reasonForAdmission = rs.getString("reason_for_admission");
                    boolean dischargeStatus = rs.getBoolean("discharge_status");

                    return new Admission(admissionId, patientId, doctorId, admissionDate, dischargeDate, roomNumber, reasonForAdmission, dischargeStatus);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // Return null if no admission found
    }

    // Method to get all admissions
    public List<Admission> getAllAdmissions() {
        List<Admission> admissions = new ArrayList<>();
        String query = "SELECT * FROM Admissions";

        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int admissionId = rs.getInt("admission_id");
                int patientId = rs.getInt("patient_id");
                int doctorId = rs.getInt("doctor_id");
                Date admissionDate = rs.getDate("admission_date");
                Date dischargeDate = rs.getDate("discharge_date");
                String roomNumber = rs.getString("room_number");
                String reasonForAdmission = rs.getString("reason_for_admission");
                boolean dischargeStatus = rs.getBoolean("discharge_status");

                Admission admission = new Admission(admissionId, patientId, doctorId, admissionDate, dischargeDate, roomNumber, reasonForAdmission, dischargeStatus);
                admissions.add(admission);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admissions;
    }

    // Method to delete an admission record
    public boolean deleteAdmission(int admissionId) {
        String query = "DELETE FROM Admissions WHERE admission_id = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, admissionId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Admission getAdmissionByPatientId(int patientId) {
        // SQL query to fetch the admission record based on patientId
        String sql = "SELECT * FROM Admissions WHERE patient_id = ?";
        try (Connection conn = DBConnection.getConnection();  PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, patientId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Extract data from the result set and create an Admission object
                int admissionId = resultSet.getInt("admission_id");
                int doctorId = resultSet.getInt("doctor_id");
                Date admissionDate = resultSet.getDate("admission_date");
                Date dischargeDate = resultSet.getDate("discharge_date");
                String roomNumber = resultSet.getString("room_number");
                String reasonForAdmission = resultSet.getString("reason_for_admission");
                boolean dischargeStatus = resultSet.getBoolean("discharge_status");

                return new Admission(admissionId, patientId, doctorId, admissionDate, dischargeDate, roomNumber, reasonForAdmission, dischargeStatus);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // If no admission is found, return null
        return null;
    }
}

