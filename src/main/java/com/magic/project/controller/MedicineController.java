package com.magic.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.magic.project.models.Medicine;
import com.magic.project.services.MedicineService;

@RestController
@RequestMapping("hospital/medicine")
public class MedicineController {

	@Autowired
	MedicineService medServ;

	// Add a medicine
	@PostMapping("/add")
	public ResponseEntity<Medicine> addmedicine(@Valid @RequestBody Medicine medicine) {
		medServ.saveMedicine(medicine);
		return ResponseEntity.status(HttpStatus.OK).body(medicine);
	}

	// delete a medicine
	@DeleteMapping("/delete/{medicineName}")
	public ResponseEntity<Medicine> deleteMedicine(@Valid @PathVariable String medicineName) {
		Medicine medicine = medServ.deleteMedicine(medicineName);
		return ResponseEntity.status(HttpStatus.OK).body(medicine);
	}

	// update a medicine by ID and Put request
	@PutMapping("/update/{medicineName}")
	public ResponseEntity<Medicine> updatemedicine(@Valid @PathVariable String medicineName,
			@RequestBody @Valid Medicine updatedmedicine) {
		Medicine medicine = medServ.updateMedicine(updatedmedicine, medicineName);
		return ResponseEntity.status(HttpStatus.OK).body(medicine);
	}

	// get list of all medicines
	@GetMapping("/list")
	public ResponseEntity<List<Medicine>> getAllmedicine() {
		List<Medicine> medicines = medServ.getMedicineList();
		return ResponseEntity.status(HttpStatus.OK).body(medicines);
	}

	// update a medicine Status by ID and Patch request
	@PatchMapping("/update/quantity/{medicineName}")
	public ResponseEntity<Medicine> updateMedicineQuantity(@Valid @PathVariable String medicineName,
			@RequestBody Medicine updatedmedicine) {
		Medicine medicine = medServ.updateMedicineQuantity(updatedmedicine, medicineName);
		return ResponseEntity.status(HttpStatus.OK).body(medicine);
	}

	// get an medicine
	@GetMapping("/get/{medicineName}")
	public ResponseEntity<Medicine> getMedicine(@PathVariable String medicineName) {
		Medicine medicine = medServ.getMedicine(medicineName);
		return ResponseEntity.status(HttpStatus.OK).body(medicine);
	}

}
