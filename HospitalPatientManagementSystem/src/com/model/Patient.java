package com.model;

import java.util.Date;

public abstract class Patient {

    // Attributes
    private int patientId;
    private String firstName;
    private String lastName;
    private Date dob;
    private String gender;
    private String address;
    private String phoneNumber;
    private String email;
    private String patientType;
    private boolean admissionStatus;

    // Constructor
    public Patient(int patientId, String firstName, String lastName, Date dob, String gender,
                   String address, String phoneNumber, String email, String patientType, boolean admissionStatus) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.gender = gender;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.patientType = patientType;
        this.admissionStatus = admissionStatus;
    }

    // Getters and Setters
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPatientType() {
        return patientType;
    }

    public void setPatientType(String patientType) {
        this.patientType = patientType;
    }

    public boolean isAdmissionStatus() {
        return admissionStatus;
    }

    public void setAdmissionStatus(boolean admissionStatus) {
        this.admissionStatus = admissionStatus;
    }

    // Abstract methods for handling patient-specific operations
    public abstract void admitPatient();
    public abstract void dischargePatient();
    public abstract double calculateBill();

    // Method to display patient details
    public String getDetails() {
        return "Patient ID: " + patientId +
               ", Name: " + firstName + " " + lastName +
               ", Date of Birth: " + dob +
               ", Gender: " + gender +
               ", Address: " + address +
               ", Phone: " + phoneNumber +
               ", Email: " + (email != null ? email : "N/A") +
               ", Patient Type: " + patientType +
               ", Admission Status: " + admissionStatus;
    }
}
