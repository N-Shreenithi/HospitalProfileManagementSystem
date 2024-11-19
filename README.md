# Hospital Patient Registration and Profile Management System

## 📜 Project Overview
The **Hospital Patient Registration and Profile Management System** is a Java-based application designed to manage hospital operations efficiently. It enables hospital staff to handle patient registrations, admissions, discharges, billing, and medical history management. The system supports both inpatient and outpatient profiles with proper data handling and validations.

---

## 🚀 Features
- **Register New Patients**: Add details for inpatients and outpatients.
- **Admit Patients**: Mark patients as admitted.
- **Discharge Patients**: Update patient records upon discharge.
- **Calculate Patient Bills**: Generate billing details based on the patient's treatments.
- **View Medical History**: Access a patient's historical medical records.
- **Error Handling**: Validates patient data and handles errors like duplicate admissions or invalid information.

---

## 🛠️ Technology Stack
- **Programming Language**: Java
- **Database**: (Add your database details here, e.g., MySQL or SQLite, if applicable)
- **Libraries**:
  - `java.text.SimpleDateFormat` for date handling.
  - Custom Exceptions: `InvalidPatientInformation`, `DoubleAdmissions`.
- **Packages**:
  - `com.model`: Contains classes like `Patient`, `InPatient`, `OutPatient`.
  - `com.service`: Contains `SystemImplementation` for business logic.
  - `com.dao`: Handles database operations with `PatientDAO`.
  - `com.exception`: Custom exception handling.

---

## 📂 Folder Structure
```plaintext
.
├── src/
│   ├── com/
│   │   ├── model/
│   │   │   ├── Patient.java
│   │   │   ├── InPatient.java
│   │   │   ├── OutPatient.java
│   │   ├── service/
│   │   │   ├── SystemImplementation.java
│   │   ├── dao/
│   │   │   ├── PatientDAO.java
│   │   ├── exception/
│   │   │   ├── InvalidPatientInformation.java
│   │   │   ├── DoubleAdmissions.java
│   ├── HospitalPatientManagementSystemApplication.java
└── README.md
```
---

## 📖 How to Use
### Main Menu
1. **Register New Patient**:
   - Enter the required patient details to register a new patient.
   - Supports both inpatient and outpatient profiles.
2. **Admit Patient**:
   - Mark a patient as admitted by their ID.
3. **Discharge Patient**:
   - Update patient records when they are discharged.
4. **Calculate Patient Bill**:
   - Calculate the total bill based on the patient's treatments.
5. **View Patient Medical History**:
   - Retrieve and display medical records of a specific patient.
6. **Exit**:
   - Exit the system.

---

## 🏁 Conclusion
This **Hospital Patient Management System** is a robust and modular solution for managing hospital operations through a console-based interface. It ensures efficient data handling, extensibility, and user-friendly interaction.

