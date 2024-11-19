package com.model;

import java.util.Date;
import java.util.Scanner; 

public class InPatient extends Patient {
    private String roomType;  // Standard, Deluxe, etc.
    private int daysStayed;   // Number of days stayed
    private String treatmentType;  // Surgery, General, etc.
    
    Scanner sc = new Scanner(System.in);
    // Constructor
    public InPatient(int patientId, String firstName, String lastName, Date dob, String gender, 
                     String address, String phoneNumber, String email, boolean admissionStatus, 
                     String roomType, int daysStayed, String treatmentType) {
        super(patientId, firstName, lastName, dob, gender, address, phoneNumber, email, "InPatient", admissionStatus);
        this.roomType = roomType;
        this.daysStayed = daysStayed;
        this.treatmentType = treatmentType;
    }

    

	public InPatient(int patientId, String firstName, String lastName, Date dob, String gender, String address,
			String phoneNumber, String email, String patientType, boolean admissionStatus) {
		super(patientId, firstName, lastName, dob, gender, address, phoneNumber, email, patientType, admissionStatus);
	}



	// Overriding calculateBill method for inpatient
    @Override
    public double calculateBill() {
        double roomCharge = 0.0;
        double treatmentCharge = 0.0;
        System.out.println("Enter room type: (Standard/ Deluxe/ VIP)");
        String roomType = sc.next();
        // Room charge based on room type
        switch (roomType) {
            case "Standard":
                roomCharge = 200.0;
                break;
            case "Deluxe":
                roomCharge = 400.0;
                break;
            case "VIP":
                roomCharge = 600.0;
                break;
            default:
                roomCharge = 200.0;
                break;
        }
        
        System.out.println("Enter treatment type: (Surgery/ General / Emergency)");
        String treatmentType = sc.next();
        
        // Treatment charge based on treatment type
        switch (treatmentType) {
            case "Surgery":
                treatmentCharge = 1500.0;
                break;
            case "General":
                treatmentCharge = 500.0;
                break;
            case "Emergency":
                treatmentCharge = 1000.0;
                break;
            default:
                treatmentCharge = 500.0;
                break;
        }

        // Calculate the total bill (room charge per day * days stayed + treatment charge)
        double totalBill = (roomCharge * daysStayed) + treatmentCharge;
        return totalBill;
    }

    @Override
    public void admitPatient() {
        System.out.println("InPatient " + getFirstName() + " " + getLastName() + " has been admitted to " + roomType + " room.");
        setAdmissionStatus(true);
    }

    @Override
    public void dischargePatient() {
        System.out.println("InPatient " + getFirstName() + " " + getLastName() + " has been discharged from " + roomType + " room.");
        setAdmissionStatus(false);
    }

	public void setRoomType(String roomType2) {
		this.roomType = roomType2;
	}

	public void setDaysStayed(int daysStayed2) {
		// TODO Auto-generated method stub
		this.daysStayed = daysStayed2;
		
	}

	public void setTreatmentType(String treatmentType2) {
		// TODO Auto-generated method stub
		this.treatmentType = treatmentType2;
		
	}
}
