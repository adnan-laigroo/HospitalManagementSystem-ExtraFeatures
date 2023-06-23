package com.magic.project.services.implementation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magic.project.handler.AppointmentNotConfirmedException;
import com.magic.project.handler.DoctorNotFoundException;
import com.magic.project.handler.PatientNotFoundException;
import com.magic.project.handler.PrescriptionNotFoundException;
import com.magic.project.models.Appointment;
import com.magic.project.models.Doctor;
import com.magic.project.models.Patient;
import com.magic.project.models.Prescription;
import com.magic.project.repositories.AppointmentRepository;
import com.magic.project.repositories.DoctorRepository;
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

		prescription.setPatientName(patient.getFirstName() + " " + patient.getLastName());
		Doctor doctor = docRepo.findById(appointment.getPatId()).orElse(null);
		if (doctor == null) {
			throw new DoctorNotFoundException("No doctor found of patient Id: " + appointment.getDocId());
		}
		prescription.setDoctorName(doctor.getFirstName() + " " + doctor.getLastName());
		presRepo.save(prescription);
	}

	@Override
	public Prescription deletePrescription(@Valid String appId) {
		Prescription prescription = presRepo.findById(appId).orElse(null);
		if (prescription == null) {
			throw new PrescriptionNotFoundException("No prescription with ID " + appId);
		}
		presRepo.deleteById(appId);
		return prescription;
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

}