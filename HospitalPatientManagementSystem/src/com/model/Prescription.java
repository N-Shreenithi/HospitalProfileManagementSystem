package com.model;

import java.util.Date;

public class Prescription {

    // Attributes
    private int prescriptionId;
    private int patientId;
    private int doctorId;
    private String medicationName;
    private String dosage;
    private Date startDate;
    private Date endDate;

    // Constructor
    public Prescription(int prescriptionId, int patientId, int doctorId, String medicationName, String dosage, Date startDate, Date endDate) {
        this.prescriptionId = prescriptionId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getters and Setters
    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
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

    public String getMedicationName() {
        return medicationName;
    }

    public void setMedicationName(String medicationName) {
        this.medicationName = medicationName;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    // Method to display prescription details
    public String getDetails() {
        return "Prescription ID: " + prescriptionId +
               ", Patient ID: " + patientId +
               ", Doctor ID: " + doctorId +
               ", Medication Name: " + medicationName +
               ", Dosage: " + dosage +
               ", Start Date: " + startDate +
               ", End Date: " + (endDate != null ? endDate : "Ongoing");
    }
}

