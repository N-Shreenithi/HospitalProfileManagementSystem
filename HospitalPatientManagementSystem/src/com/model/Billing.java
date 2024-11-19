package com.model;

import java.util.Date;

public class Billing {

    // Attributes
    private int billId;
    private int patientId;
    private int admissionId;
    private double totalAmount;
    private boolean paymentStatus;
    private Date billDate;

    // Constructor
    public Billing(int billId, int patientId, int admissionId, double totalAmount, boolean paymentStatus, Date billDate) {
        this.billId = billId;
        this.patientId = patientId;
        this.admissionId = admissionId;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
        this.billDate = billDate;
    }

    // Getters and Setters
    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getAdmissionId() {
        return admissionId;
    }

    public void setAdmissionId(int admissionId) {
        this.admissionId = admissionId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    // Method to mark the bill as paid
    public void markAsPaid() {
        this.paymentStatus = true;
        System.out.println("Bill has been marked as paid.");
    }

    // Method to display billing details
    public String getDetails() {
        return "Bill ID: " + billId +
               ", Patient ID: " + patientId +
               ", Admission ID: " + admissionId +
               ", Total Amount: $" + totalAmount +
               ", Payment Status: " + (paymentStatus ? "Paid" : "Unpaid") +
               ", Bill Date: " + billDate;
    }
}
