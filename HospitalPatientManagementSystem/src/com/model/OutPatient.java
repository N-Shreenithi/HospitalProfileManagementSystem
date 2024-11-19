package com.model;

import java.util.Date;

public class OutPatient extends Patient {
    private String treatmentType;  // Consultation, Procedure, etc.
    
    // Constructor
    public OutPatient(int patientId, String firstName, String lastName, Date dob, String gender, 
                      String address, String phoneNumber, String email, boolean admissionStatus, 
                      String treatmentType) {
        super(patientId, firstName, lastName, dob, gender, address, phoneNumber, email, "OutPatient", admissionStatus);
        this.treatmentType = treatmentType;
    }

    // Overriding calculateBill method for outpatient
    @Override
    public double calculateBill() {
        double consultationFee = 300.0; // Flat consultation fee
        double procedureFee = 0.0;

        // Procedure fee based on treatment type
        switch (treatmentType) {
            case "Check-up":
                procedureFee = 100.0;
                break;
            case "Procedure":
                procedureFee = 500.0;
                break;
            case "Emergency":
                procedureFee = 700.0;
                break;
            default:
                procedureFee = 100.0;
                break;
        }

        // Total bill for outpatient (consultation + treatment)
        double totalBill = consultationFee + procedureFee;
        return totalBill;
    }

    public OutPatient(int patientId, String firstName, String lastName, Date dob, String gender, String address,
			String phoneNumber, String email, String patientType, boolean admissionStatus) {
		super(patientId, firstName, lastName, dob, gender, address, phoneNumber, email, patientType, admissionStatus);
	}

	@Override
    public void admitPatient() {
        System.out.println("OutPatient " + getFirstName() + " " + getLastName() + " is admitted for consultation.");
        setAdmissionStatus(true);
    }

    @Override
    public void dischargePatient() {
        System.out.println("OutPatient " + getFirstName() + " " + getLastName() + " is discharged after consultation.");
        setAdmissionStatus(false);
    }
}
