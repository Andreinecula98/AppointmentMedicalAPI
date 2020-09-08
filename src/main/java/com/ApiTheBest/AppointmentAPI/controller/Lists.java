package com.ApiTheBest.AppointmentAPI.controller;

import com.ApiTheBest.AppointmentAPI.model.doctorModel.Doctor;
import com.ApiTheBest.AppointmentAPI.model.patientModel.Patient;

import java.util.ArrayList;
import java.util.List;

public class Lists {

    public static java.util.List<Patient> patients = new ArrayList<>();
    public static java.util.List<Doctor> doctors = new ArrayList<>();

    static {

        Patient patient1 = new Patient(1, "John", "LA",
                "john@gmail.com", "0745896358");
        Patient patient2 = new Patient(2, "Dana", "ND",
                "dana@gmail.com", "0769896452");
        Patient patient3 = new Patient(3, "Julia", "AZ",
                "julia@gmail.com", "0765698236");
        patients.add(patient1);
        patients.add(patient2);
        patients.add(patient3);
    }

    static {
        doctors.add(new Doctor(1, "Luiza", "0750883434", "blabla", "medic"));
        doctors.add(new Doctor(2, "Luiz", "0750883435", "blabla", "medic"));
    }

    public static List<Patient> getPatients() {
        return patients;
    }

    public static void setPatients(List<Patient> patients) {
        Lists.patients = patients;
    }

    public static List<Doctor> getDoctors() {
        return doctors;
    }

    public static void setDoctors(List<Doctor> doctors) {
        Lists.doctors = doctors;
    }
}
