package com.service;

import com.model.Patient;
import com.model.Admission;
import com.model.Billing;
import com.model.MedicalHistory;
import com.exception.InvalidPatientInformation;
import com.exception.DoubleAdmissions;

import java.util.List;

public interface SystemInterface {
    void admitPatient(Patient patient) throws InvalidPatientInformation, DoubleAdmissions;
    void dischargePatient(int patientId) throws InvalidPatientInformation;
    double calculateBill(int patientId) throws InvalidPatientInformation;
    List<MedicalHistory> viewMedicalHistory(int patientId) throws InvalidPatientInformation;
}
