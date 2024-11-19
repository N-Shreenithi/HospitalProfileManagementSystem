package com.dao;

import com.model.Patient;
import com.model.InPatient;  // Import InPatient and OutPatient classes
import com.model.OutPatient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    // Method to add a new patient
    public boolean addPatient(Patient patient) {
        String query = "INSERT INTO Patients (first_name, last_name, dob, gender, address, phone_number, email, patient_type, admission_status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, patient.getFirstName());
            stmt.setString(2, patient.getLastName());
            stmt.setDate(3, new java.sql.Date(patient.getDob().getTime()));
            stmt.setString(4, patient.getGender());
            stmt.setString(5, patient.getAddress());
            stmt.setString(6, patient.getPhoneNumber());
            stmt.setString(7, patient.getEmail());
            stmt.setString(8, patient.getPatientType());
            stmt.setBoolean(9, patient.isAdmissionStatus());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to update an existing patient record
    public boolean updatePatient(Patient patient) {
        String query = "UPDATE Patients SET first_name = ?, last_name = ?, dob = ?, gender = ?, address = ?, phone_number = ?, email = ?, patient_type = ?, admission_status = ? WHERE patient_id = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, patient.getFirstName());
            stmt.setString(2, patient.getLastName());
            stmt.setDate(3, new java.sql.Date(patient.getDob().getTime()));
            stmt.setString(4, patient.getGender());
            stmt.setString(5, patient.getAddress());
            stmt.setString(6, patient.getPhoneNumber());
            stmt.setString(7, patient.getEmail());
            stmt.setString(8, patient.getPatientType());
            stmt.setBoolean(9, patient.isAdmissionStatus());
            stmt.setInt(10, patient.getPatientId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to get a patient record by its ID
    public Patient getPatientById(int patientId) {
        String query = "SELECT * FROM Patients WHERE patient_id = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, patientId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Create the appropriate Patient subclass (InPatient or OutPatient)
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    Date dob = rs.getDate("dob");
                    String gender = rs.getString("gender");
                    String address = rs.getString("address");
                    String phoneNumber = rs.getString("phone_number");
                    String email = rs.getString("email");
                    String patientType = rs.getString("patient_type");
                    boolean admissionStatus = rs.getBoolean("admission_status");

                    // Check the patient type and return the appropriate subclass
                    if ("InPatient".equalsIgnoreCase(patientType)) {
                        // Providing default values for roomType, daysStayed, and treatmentType
                        return new InPatient(patientId, firstName, lastName, dob, gender, address, phoneNumber, email, admissionStatus, null, 0, null);
                    } else if ("OutPatient".equalsIgnoreCase(patientType)) {
                        return new OutPatient(patientId, firstName, lastName, dob, gender, address, phoneNumber, email, admissionStatus, null);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if no patient found
    }

    // Method to get all patients
    public List<Patient> getAllPatients() {
        List<Patient> patients = new ArrayList<>();
        String query = "SELECT * FROM Patients";

        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int patientId = rs.getInt("patient_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                Date dob = rs.getDate("dob");
                String gender = rs.getString("gender");
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phone_number");
                String email = rs.getString("email");
                String patientType = rs.getString("patient_type");
                boolean admissionStatus = rs.getBoolean("admission_status");

                // Check the patient type and add the appropriate subclass
                if ("InPatient".equalsIgnoreCase(patientType)) {
                    // Providing default values for roomType, daysStayed, and treatmentType
                    patients.add(new InPatient(patientId, firstName, lastName, dob, gender, address, phoneNumber, email, admissionStatus, null, 0, null));
                } else if ("OutPatient".equalsIgnoreCase(patientType)) {
                    patients.add(new OutPatient(patientId, firstName, lastName, dob, gender, address, phoneNumber, email, admissionStatus, null));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patients;
    }

    // Method to delete a patient record
    public boolean deletePatient(int patientId) {
        String query = "DELETE FROM Patients WHERE patient_id = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, patientId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
