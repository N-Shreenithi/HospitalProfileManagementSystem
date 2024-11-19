package com.model;

import java.util.Date;

public class Admission {

    // Attributes
    private int admissionId;
    private int patientId;
    private int doctorId;
    private Date admissionDate;
    private Date dischargeDate;
    private String roomNumber;
    private String reasonForAdmission;
    private boolean dischargeStatus;

    // Constructor
    public Admission(int admissionId, int patientId, int doctorId, Date admissionDate, Date dischargeDate,
                     String roomNumber, String reasonForAdmission, boolean dischargeStatus) {
        this.admissionId = admissionId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.roomNumber = roomNumber;
        this.reasonForAdmission = reasonForAdmission;
        this.dischargeStatus = dischargeStatus;
    }
    
    public Admission(int patientId, Date admissionDate, Date dischargeDate) {
        this.patientId = patientId;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        
    }


    // Getters and Setters
    public int getAdmissionId() {
        return admissionId;
    }

    public void setAdmissionId(int admissionId) {
        this.admissionId = admissionId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Date getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getReasonForAdmission() {
        return reasonForAdmission;
    }

    public void setReasonForAdmission(String reasonForAdmission) {
        this.reasonForAdmission = reasonForAdmission;
    }

    public boolean isDischargeStatus() {
        return dischargeStatus;
    }

    public void setDischargeStatus(boolean dischargeStatus) {
        this.dischargeStatus = dischargeStatus;
    }

    // Method to admit a patient
    public void admitPatient() {
        this.admissionDate = new Date(); // Current date and time for admission
        this.dischargeStatus = false;
        System.out.println("Patient admitted with reason: " + reasonForAdmission);
    }

    // Method to discharge a patient
    public void dischargePatient() {
        this.dischargeDate = new Date(); // Current date and time for discharge
        this.dischargeStatus = true;
        System.out.println("Patient discharged.");
    }

    // Method to display admission details
    public String getDetails() {
        return "Admission ID: " + admissionId +
               ", Patient ID: " + patientId +
               ", Doctor ID: " + doctorId +
               ", Admission Date: " + admissionDate +
               ", Discharge Date: " + (dischargeDate != null ? dischargeDate : "N/A") +
               ", Room Number: " + roomNumber +
               ", Reason for Admission: " + reasonForAdmission +
               ", Discharge Status: " + dischargeStatus;
    }
}
