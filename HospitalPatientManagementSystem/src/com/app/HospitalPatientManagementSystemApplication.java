package com.app;

import java.text.SimpleDateFormat;
import com.model.*;
import com.service.*;
import com.exception.InvalidPatientInformation;
import com.dao.PatientDAO;
import com.exception.DoubleAdmissions;
import java.util.Scanner;
import java.util.Date;

public class HospitalPatientManagementSystemApplication {

    public static void main(String[] args) {
        SystemImplementation systemService = new SystemImplementation();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Hospital Patient Management System ---");
            System.out.println("1. Register New Patient");
            System.out.println("2. Admit Patient");
            System.out.println("3. Discharge Patient");
            System.out.println("4. Calculate Patient Bill");
            System.out.println("5. View Patient Medical History");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
            case 1:
                // Register New Patient
                try {
                    System.out.print("Enter Patient ID: ");
                    int patientId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter First Name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Enter Last Name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Enter2 Date of Birth (yyyy-mm-dd): ");
                    String dob = scanner.next();
                    scanner.nextLine(); // Consume newline

                    // Parse dob to a java.util.Date object
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date dobDate = dateFormat.parse(dob);

                    System.out.print("Enter Gender: ");
                    String gender = scanner.nextLine();
                    System.out.print("Enter Address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter Phone Number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Patient Type (InPatient/OutPatient): ");
                    String patientType = scanner.nextLine();
                    
                    
                    
                    Patient patient;
                    if ("InPatient".equalsIgnoreCase(patientType)) {
                        patient = new InPatient(patientId, firstName, lastName, dobDate, gender, address, phoneNumber, email, "InPatient",false);
                        PatientDAO patientDAO = new PatientDAO();
                        patientDAO.addPatient(patient);
                        System.out.println("Patient registered successfully!! ");
                    } else {
                    	System.out.println("Enter the treatment type: (Check-up/ Procedure/ Emergency)");
                    	String treatmentType = scanner.nextLine();
                        patient = new OutPatient(patientId, firstName, lastName, dobDate, gender, address, phoneNumber, email, "OutPatient", false);
                        PatientDAO patientDAO = new PatientDAO();
                        patientDAO.addPatient(patient);
                        System.out.println("Patient registered successfully!! ");
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

            case 2: // Admit Patient
                try {
                    System.out.print("Enter Patient ID: ");
                    int patientId = scanner.nextInt();
                    PatientDAO patientDAO = new PatientDAO();  // Instantiate PatientDAO directly
                    Patient patient = patientDAO.getPatientById(patientId);  // Directly call DAO method
                    if (patient != null) {
                        systemService.admitPatient(patient);  // Proceed with the admission
                    } else {
                        System.out.println("Patient not found.");
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;

                case 3:
                    // Discharge Patient
                    try {
                        System.out.print("Enter Patient ID: ");
                        int patientId = scanner.nextInt();
                        systemService.dischargePatient(patientId);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 4:
                    // Calculate Patient Bill
                    try {
                        System.out.print("Enter Patient ID: ");
                        int patientId = scanner.nextInt();
                        double billAmount = systemService.calculateBill(patientId);
                        System.out.println("Total Bill Amount: $" + billAmount);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 5:
                    // View Patient Medical History
                    try {
                        System.out.print("Enter Patient ID: ");
                        int patientId = scanner.nextInt();
                        systemService.viewMedicalHistory(patientId);
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 6:
                    // Exit
                    exit = true;
                    System.out.println("Exiting system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
        scanner.close();
    }
}
