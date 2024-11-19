package com.model;

public class Doctor {

    // Attributes
    private int doctorId;
    private String firstName;
    private String lastName;
    private String specialization;
    private String phoneNumber;
    private String email;

    // Constructor
    public Doctor(int doctorId, String firstName, String lastName, String specialization, String phoneNumber, String email) {
        this.doctorId = doctorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // Getters and Setters
    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
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

    // Method to display doctor details
    public String getDetails() {
        return "Doctor ID: " + doctorId +
               ", Name: " + firstName + " " + lastName +
               ", Specialization: " + specialization +
               ", Phone: " + phoneNumber +
               ", Email: " + (email != null ? email : "N/A");
    }
}
