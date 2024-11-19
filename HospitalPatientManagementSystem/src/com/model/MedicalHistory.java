package com.model;

import java.util.Date;

public class MedicalHistory {

    // Attributes
    private int historyId;
    private int patientId;
    private String medicalCondition;
    private String treatment;
    private Date treatmentStartDate;
    private Date treatmentEndDate;

    // Constructor
    public MedicalHistory(int historyId, int patientId, String medicalCondition, String treatment, Date treatmentStartDate, Date treatmentEndDate) {
        this.historyId = historyId;
        this.patientId = patientId;
        this.medicalCondition = medicalCondition;
        this.treatment = treatment;
        this.treatmentStartDate = treatmentStartDate;
        this.treatmentEndDate = treatmentEndDate;
    }

    // Getters and Setters
    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getMedicalCondition() {
        return medicalCondition;
    }

    public void setMedicalCondition(String medicalCondition) {
        this.medicalCondition = medicalCondition;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public Date getTreatmentStartDate() {
        return treatmentStartDate;
    }

    public void setTreatmentStartDate(Date treatmentStartDate) {
        this.treatmentStartDate = treatmentStartDate;
    }

    public Date getTreatmentEndDate() {
        return treatmentEndDate;
    }

    public void setTreatmentEndDate(Date treatmentEndDate) {
        this.treatmentEndDate = treatmentEndDate;
    }

    // Method to display medical history details
    public String getDetails() {
        return "History ID: " + historyId +
               ", Patient ID: " + patientId +
               ", Medical Condition: " + medicalCondition +
               ", Treatment: " + treatment +
               ", Treatment Start Date: " + treatmentStartDate +
               ", Treatment End Date: " + (treatmentEndDate != null ? treatmentEndDate : "Ongoing");
    }
}
