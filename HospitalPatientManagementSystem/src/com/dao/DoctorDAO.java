package com.dao;

import com.model.Doctor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    // Method to add a new doctor record
    public boolean addDoctor(Doctor doctor) {
        String query = "INSERT INTO Doctors (first_name, last_name, specialization, phone_number, email) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, doctor.getFirstName());
            stmt.setString(2, doctor.getLastName());
            stmt.setString(3, doctor.getSpecialization());
            stmt.setString(4, doctor.getPhoneNumber());
            stmt.setString(5, doctor.getEmail());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to update an existing doctor record
    public boolean updateDoctor(Doctor doctor) {
        String query = "UPDATE Doctors SET first_name = ?, last_name = ?, specialization = ?, phone_number = ?, email = ? WHERE doctor_id = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, doctor.getFirstName());
            stmt.setString(2, doctor.getLastName());
            stmt.setString(3, doctor.getSpecialization());
            stmt.setString(4, doctor.getPhoneNumber());
            stmt.setString(5, doctor.getEmail());
            stmt.setInt(6, doctor.getDoctorId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to get a doctor record by its ID
    public Doctor getDoctorById(int doctorId) {
        String query = "SELECT * FROM Doctors WHERE doctor_id = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, doctorId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    String specialization = rs.getString("specialization");
                    String phoneNumber = rs.getString("phone_number");
                    String email = rs.getString("email");

                    return new Doctor(doctorId, firstName, lastName, specialization, phoneNumber, email);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if no doctor found
    }

    // Method to get all doctor records
    public List<Doctor> getAllDoctors() {
        List<Doctor> doctors = new ArrayList<>();
        String query = "SELECT * FROM Doctors";

        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int doctorId = rs.getInt("doctor_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String specialization = rs.getString("specialization");
                String phoneNumber = rs.getString("phone_number");
                String email = rs.getString("email");

                Doctor doctor = new Doctor(doctorId, firstName, lastName, specialization, phoneNumber, email);
                doctors.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return doctors;
    }

    // Method to delete a doctor record
    public boolean deleteDoctor(int doctorId) {
        String query = "DELETE FROM Doctors WHERE doctor_id = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, doctorId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
