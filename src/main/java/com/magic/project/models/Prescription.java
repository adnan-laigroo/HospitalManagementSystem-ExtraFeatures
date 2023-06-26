package com.magic.project.models;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

@Entity
public class Prescription {
	//appointment id and prescription id are same
	@Id
	@Pattern(regexp = "^A\\d{6}$", message = "Invalid Appointment ID format")
	private String apId;

	private String patientName;

	private String doctorName;

	private String symptom;

	private String diagnosis;
	
	@ElementCollection
	private List<PrescribedMedicine> prescribedMedicines;

	@ElementCollection
	private List<PrescribedTest> prescribedTests;

	private String notes;

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getApId() {
		return apId;
	}

	public void setApId(String apId) {
		this.apId = apId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getSymptom() {
		return symptom;
	}

	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}

	public List<PrescribedMedicine> getPrescribedMedicines() {
		return prescribedMedicines;
	}

	public void setPrescribedMedicines(List<PrescribedMedicine> prescribedMedicines) {
		this.prescribedMedicines = prescribedMedicines;
	}

	public List<PrescribedTest> getPrescribedTests() {
		return prescribedTests;
	}

	public void setPrescribedTests(List<PrescribedTest> prescribedTests) {
		this.prescribedTests = prescribedTests;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

}
