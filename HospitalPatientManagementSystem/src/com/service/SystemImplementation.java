package com.service;

import com.model.*;
import java.util.*;
import com.dao.*;
import com.exception.InvalidPatientInformation;
import com.exception.DoubleAdmissions;
import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import com.model.Admission;
import java.text.SimpleDateFormat;

public class SystemImplementation implements SystemInterface {

    private PatientDAO patientDAO;
    private AdmissionDAO admissionDAO;
    private BillingDAO billingDAO;
    private MedicalHistoryDAO medicalHistoryDAO;
    private PrescriptionDAO prescriptionDAO;

    public SystemImplementation() {
        this.patientDAO = new PatientDAO();
        this.admissionDAO = new AdmissionDAO();
        this.billingDAO = new BillingDAO();
        this.medicalHistoryDAO = new MedicalHistoryDAO();
        this.prescriptionDAO = new PrescriptionDAO();
    }
    
    Scanner sc = new Scanner(System.in);
    
    @Override
    public void admitPatient(Patient patient) throws InvalidPatientInformation, DoubleAdmissions {
        // Validate patient data
        if (patient == null) {
            throw new InvalidPatientInformation("Invalid patient information.");
        }
        else {
        	
        	System.out.println("Enter admissionId: ");
        	int admissionId=sc.nextInt();
            int patientId=patient.getPatientId();
            System.out.println("Enter the doctorId: ");
        	int doctorId=sc.nextInt();
        	
        	try {
	        	System.out.println("Enter the admissionDate: (YYYY-MM-DD) ");
	        	String admissionDate = sc.next();
	        	
	        	SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");
	        	Date addate = formatter.parse(admissionDate);
	        	
	        	System.out.println("Enter the dischargeDate: (YYYY-MM-DD) ");
	            String dischargeDate = sc.next();
	        	Date disdate = formatter.parse(dischargeDate);
        	
	            System.out.println("Enter the roomNumber: ");
	            String roomNumber = sc.next();
	            
	            System.out.println("Enter the reason for admission: ");
	            String reasonforAdmission = sc.nextLine();
	            
	            Admission admission = new Admission(admissionId, patientId, doctorId, addate, disdate, roomNumber, reasonforAdmission, false);
            
	            admissionDAO.addAdmission(admission);

	            System.out.println("Patient admitted successfully.");
        
        	} catch (Exception e) {
        		System.out.println(e+" ");
        	}
        	
        }
    }

    @Override
    public void dischargePatient(int patientId) throws InvalidPatientInformation {
        // Fetch patient from PatientDAO
        Patient patient = patientDAO.getPatientById(patientId);
        if (patient == null) {
            throw new InvalidPatientInformation("No patient found with this ID.");
        }

        // Fetch admission details based on patientId
        Admission admission = admissionDAO.getAdmissionByPatientId(patientId);
        if (admission == null) {
            throw new InvalidPatientInformation("No admission record found for this patient.");
        }

        System.out.println("Enter admissionid: ");
        Scanner sc = new Scanner(System.in);
        int admissionid = sc.nextInt();
        admissionDAO.deleteAdmission(admissionid);

        System.out.println("Patient discharged successfully.");
    }


    @Override
    public double calculateBill(int patientId) throws InvalidPatientInformation {
        // Fetch patient details
        Patient patient = patientDAO.getPatientById(patientId);
        if (patient == null) {
            throw new InvalidPatientInformation("No patient found with this ID.");
        }

        double totalAmount = 0.0;
        int admissionId = 0;  // Variable to store admissionId
        boolean paymentStatus = false;  // Set default or random payment status
        Date billDate = new Date();  // Current date

        // Fetch admissionId for the patient (assuming admissionDAO has getAdmissionByPatientId method)
        Admission admission = admissionDAO.getAdmissionByPatientId(patientId);
        if (admission != null) {
            admissionId = admission.getAdmissionId();  // Get the admissionId from Admission object
        } else {
            throw new InvalidPatientInformation("No admission record found for this patient.");
        }

        // Bill calculation logic
        if (patient instanceof InPatient) {
            InPatient inPatient = (InPatient) patient;
            totalAmount = inPatient.calculateBill(); // Call the InPatient's calculateBill method
        } else if (patient instanceof OutPatient) {
            OutPatient outPatient = (OutPatient) patient;
            totalAmount = outPatient.calculateBill(); // Call the OutPatient's calculateBill method
        }

        // For demonstration, let's randomly assign paymentStatus (could be based on user input)
        paymentStatus = new Random().nextBoolean();  // Randomly sets paymentStatus to true or false

        // Create a new Billing object using the constructor
        Billing billing = new Billing(0, patientId, admissionId, totalAmount, paymentStatus, billDate);
        
        // Add the billing information to the database
        billingDAO.addBill(billing);

        // Output the total bill
        System.out.println("Total bill for patient " + patientId + ": " + totalAmount);

        return totalAmount;
    }


    @Override
    public List<MedicalHistory> viewMedicalHistory(int patientId) throws InvalidPatientInformation {
        // Retrieve medical history for the patient
        List<MedicalHistory> medicalHistoryList = medicalHistoryDAO.getMedicalHistoryByPatientId(patientId);
        if (medicalHistoryList == null || medicalHistoryList.isEmpty()) {
            throw new InvalidPatientInformation("No medical history found for this patient.");
        }

        System.out.println("Medical history for patient " + patientId + ":");
        for (MedicalHistory history : medicalHistoryList) {
            System.out.println("Medical Condition: " + history.getMedicalCondition());
            System.out.println("Treatment: " + history.getTreatment());
            System.out.println("Start Date: " + history.getTreatmentStartDate());
            System.out.println("End Date: " + history.getTreatmentEndDate());
            System.out.println("--------------");
        }
        return medicalHistoryList;
    }
}
