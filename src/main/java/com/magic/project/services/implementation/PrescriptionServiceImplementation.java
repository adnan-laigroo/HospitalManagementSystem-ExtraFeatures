package com.magic.project.services.implementation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.DocumentException;
import com.magic.project.handler.AppointmentNotConfirmedException;
import com.magic.project.handler.DoctorNotFoundException;
import com.magic.project.handler.MedicalTestNotFoundException;
import com.magic.project.handler.MedicineNotFoundException;
import com.magic.project.handler.PatientNotFoundException;
import com.magic.project.handler.PrescriptionNotFoundException;
import com.magic.project.models.Appointment;
import com.magic.project.models.Doctor;
import com.magic.project.models.MedicalTest;
import com.magic.project.models.Medicine;
import com.magic.project.models.Patient;
import com.magic.project.models.PrescribedMedicine;
import com.magic.project.models.PrescribedTest;
import com.magic.project.models.Prescription;
import com.magic.project.models.dto.PrescriptionGenerator;
import com.magic.project.repositories.AppointmentRepository;
import com.magic.project.repositories.DoctorRepository;
import com.magic.project.repositories.MedicalTestRepository;
import com.magic.project.repositories.MedicineRepository;
import com.magic.project.repositories.PatientRepository;
import com.magic.project.repositories.PrescriptionRepository;
import com.magic.project.services.PrescriptionService;

@Service
public class PrescriptionServiceImplementation implements PrescriptionService {

	@Autowired
	PrescriptionRepository presRepo;
	@Autowired
	PatientRepository patRepo;
	@Autowired
	AppointmentRepository appRepo;
	@Autowired
	DoctorRepository docRepo;
	@Autowired
	MedicalTestRepository testRepo;
	@Autowired
	MedicineRepository medRepo;

	@Override
	public void savePrescription(@Valid Prescription prescription) {
		Appointment appointment = appRepo.findById(prescription.getApId()).orElse(null);
		if (appointment == null) {
			throw new AppointmentNotConfirmedException("No appointment found of patient Id: " + prescription.getApId());
		}

		Patient patient = patRepo.findById(appointment.getPatId()).orElse(null);
		if (patient == null) {
			throw new PatientNotFoundException("No patient found of patient Id: " + appointment.getPatId());
		}
		prescription.setSymptom(patient.getSymptom());
		prescription.setPatientName(patient.getFirstName() + " " + patient.getLastName());
		Doctor doctor = docRepo.findById(appointment.getDocId()).orElse(null);
		if (doctor == null) {
			throw new DoctorNotFoundException("No doctor found of doctor Id: " + appointment.getDocId());
		}
		prescription.setDoctorName("Dr." + " " + doctor.getFirstName() + " " + doctor.getLastName());

		for (PrescribedTest test : prescription.getPrescribedTests()) {
			MedicalTest medicalTest = testRepo.findById(test.getTestName()).orElse(null);
			if (medicalTest == null) {
				throw new MedicalTestNotFoundException("No medical test found with name: " + test.getTestName());
			}
			test.setTestResult("Awaiting");
		}
		for (PrescribedMedicine med : prescription.getPrescribedMedicines()) {
			Medicine medicine = medRepo.findById(med.getMedicineName()).orElse(null);
			if (medicine == null) {
				throw new MedicineNotFoundException("No medicine found with name: " + med.getMedicineName());
			}
		}

		presRepo.save(prescription);
	}

	@Override
	public Prescription deletePrescription(@Valid String appId) {
		Prescription deletedPrescription = presRepo.findById(appId).orElse(null);
		if (deletedPrescription == null) {
			throw new PrescriptionNotFoundException("No prescription with ID " + appId);
		}
		deletedPrescription.getPrescribedMedicines().size();
		deletedPrescription.getPrescribedTests().size();
		presRepo.deleteById(appId);
		return deletedPrescription;
	}

	@Override
	public Prescription updatePrescription(@Valid Prescription updatedPrescription, @Valid String appId) {
		Prescription prescription = presRepo.findById(appId).orElse(null);
		if (prescription == null) {
			throw new PrescriptionNotFoundException("No prescription with ID " + appId);
		}
		updatedPrescription.setApId(appId);
		presRepo.save(updatedPrescription);
		return updatedPrescription;
	}

	@Override
	public List<Prescription> getPrescriptionList() {
		List<Prescription> prescriptions = presRepo.findAll();
		if (prescriptions.isEmpty()) {
			throw new PrescriptionNotFoundException("No Prescriptions.");
		}
		return prescriptions;
	}

	@Override
	public Prescription updatePrescriptionTestResults(@Valid Prescription updatedPrescription, @Valid String appId) {
		Prescription prescription = presRepo.findById(appId).orElse(null);
		if (prescription == null) {
			throw new PrescriptionNotFoundException("No prescription with ID " + appId);
		}
		prescription.setPrescribedTests(updatedPrescription.getPrescribedTests());
		presRepo.save(prescription);
		return prescription;
	}

	@Override
	public Prescription getPrescription(String appId) {
		Prescription prescription = presRepo.findById(appId).orElse(null);
		if (prescription == null) {
			throw new PrescriptionNotFoundException("No prescription with ID " + appId);
		}
		return prescription;
	}

	@Override
	public byte[] generatePrescriptionPDF(String appId) throws DocumentException{
		Prescription prescription = presRepo.findById(appId).orElse(null);
		if (prescription == null) {
			throw new PrescriptionNotFoundException("No prescription with ID " + appId);
		}
		return PrescriptionGenerator.generatePrescriptionPDF(prescription);
	}

}