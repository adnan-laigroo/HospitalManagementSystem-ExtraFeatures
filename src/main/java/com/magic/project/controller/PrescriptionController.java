package com.magic.project.controller;
import com.magic.project.models.Prescription;
import com.magic.project.services.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("hospital/prescription")
public class PrescriptionController {
	@Autowired
	PrescriptionService presServ;

	// Add a Prescription
	@PostMapping("/add")
	public ResponseEntity<Prescription> addPrescription(@Valid @RequestBody Prescription prescription) {
		presServ.savePrescription(prescription);
		return ResponseEntity.status(HttpStatus.OK).body(prescription);
	}

	// delete a Prescription
	@DeleteMapping("/delete/{appId}")
	public ResponseEntity<Prescription> deletePrescription(@Valid @PathVariable String appId) {
		Prescription delPrescription= new Prescription();
		 delPrescription = presServ.deletePrescription(appId);
		return ResponseEntity.status(HttpStatus.OK).body(delPrescription);
	}
	

	// update a Prescription by ID and Put request
	@PutMapping("/update/{appId}")
	public ResponseEntity<Prescription> updatePrescription(@Valid @PathVariable String appId,
			@RequestBody @Valid Prescription updatedPrescription) {
		Prescription prescription = presServ.updatePrescription(updatedPrescription, appId);
		return ResponseEntity.status(HttpStatus.OK).body(prescription);
	}

	// get list of all Prescriptions
	@GetMapping("/list")
	public ResponseEntity<List<Prescription>> getAllPrescription() {
		List<Prescription> prescriptions = presServ.getPrescriptionList();
		return ResponseEntity.status(HttpStatus.OK).body(prescriptions);
	}

	// update a Prescription Status by ID and Patch request
	@PatchMapping("/update/test/{appId}")
	public ResponseEntity<Prescription> updatePrescriptionTestResults(@Valid @PathVariable String appId,
			@RequestBody Prescription updatedPrescription) {
		Prescription prescription = presServ.updatePrescriptionTestResults(updatedPrescription, appId);
		return ResponseEntity.status(HttpStatus.OK).body(prescription);
	}

	// get an Prescription
	@GetMapping("/get/{appId}")
	public ResponseEntity<Prescription> getPrescription(@PathVariable String appId) {
		Prescription prescription = presServ.getPrescription(appId);
		return ResponseEntity.status(HttpStatus.OK).body(prescription);
	}

}