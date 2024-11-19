package com.dao;

import com.model.Billing;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillingDAO {

    // Method to add a new billing record
    public boolean addBill(Billing billing) {
        String query = "INSERT INTO Billing (patient_id, admission_id, total_amount, payment_status, bill_date) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, billing.getPatientId());
            stmt.setInt(2, billing.getAdmissionId());
            stmt.setDouble(3, billing.getTotalAmount());
            stmt.setBoolean(4, billing.isPaymentStatus());
            stmt.setTimestamp(5, new Timestamp(billing.getBillDate().getTime())); // Using java.sql.Timestamp for bill date

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to update an existing billing record
    public boolean updateBill(Billing billing) {
        String query = "UPDATE Billing SET total_amount = ?, payment_status = ?, bill_date = ? WHERE bill_id = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDouble(1, billing.getTotalAmount());
            stmt.setBoolean(2, billing.isPaymentStatus());
            stmt.setTimestamp(3, new Timestamp(billing.getBillDate().getTime())); // Using java.sql.Timestamp for bill date
            stmt.setInt(4, billing.getBillId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to get a billing record by its ID
    public Billing getBillById(int billId) {
        String query = "SELECT * FROM Billing WHERE bill_id = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, billId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int patientId = rs.getInt("patient_id");
                    int admissionId = rs.getInt("admission_id");
                    double totalAmount = rs.getDouble("total_amount");
                    boolean paymentStatus = rs.getBoolean("payment_status");
                    Timestamp billDate = rs.getTimestamp("bill_date"); // Get java.sql.Timestamp from ResultSet

                    return new Billing(billId, patientId, admissionId, totalAmount, paymentStatus, new Date(billDate.getTime()));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if no bill found
    }

    // Method to get all billing records
    public List<Billing> getAllBills() {
        List<Billing> bills = new ArrayList<>();
        String query = "SELECT * FROM Billing";

        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int billId = rs.getInt("bill_id");
                int patientId = rs.getInt("patient_id");
                int admissionId = rs.getInt("admission_id");
                double totalAmount = rs.getDouble("total_amount");
                boolean paymentStatus = rs.getBoolean("payment_status");
                Timestamp billDate = rs.getTimestamp("bill_date"); // Get java.sql.Timestamp from ResultSet

                Billing bill = new Billing(billId, patientId, admissionId, totalAmount, paymentStatus, new Date(billDate.getTime()));
                bills.add(bill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bills;
    }

    // Method to delete a billing record
    public boolean deleteBill(int billId) {
        String query = "DELETE FROM Billing WHERE bill_id = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, billId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
