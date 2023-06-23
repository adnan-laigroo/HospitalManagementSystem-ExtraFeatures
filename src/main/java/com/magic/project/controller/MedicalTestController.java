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

import com.magic.project.models.MedicalTest;
import com.magic.project.services.MedicalTestService;

@RestController
@RequestMapping("hospital/medicalTest")
public class MedicalTestController {

	@Autowired
	MedicalTestService testServ;

	// Add a MedicalTest
	@PostMapping("/add")
	public ResponseEntity<MedicalTest> addMedicalTest(@Valid @RequestBody MedicalTest MedicalTest) {
		testServ.savemedicalTest(MedicalTest);
		return ResponseEntity.status(HttpStatus.OK).body(medicalTest);
	}

	// delete a MedicalTest
	@DeleteMapping("/delete/{testName}")
	public ResponseEntity<MedicalTest> deleteMedicalTest(@Valid @PathVariable String testName) {
		MedicalTest MedicalTest = testServ.deleteMedicalTest(testName);
		return ResponseEntity.status(HttpStatus.OK).body(MedicalTest);
	}

	// update a MedicalTest by ID and Put request
	@PutMapping("/update/{testName}")
	public ResponseEntity<MedicalTest> updateMedicalTest(@Valid @PathVariable String testName,
			@RequestBody @Valid MedicalTest updatedMedicalTest) {
		MedicalTest medicalTest = testServ.updateMedicalTest(updatedMedicalTest, testName);
		return ResponseEntity.status(HttpStatus.OK).body(medicalTest);
	}

	// get list of all MedicalTests
	@GetMapping("/list")
	public ResponseEntity<List<MedicalTest>> getAllMedicalTest() {
		List<MedicalTest> medicalTests = testServ.getMedicalTestList();
		return ResponseEntity.status(HttpStatus.OK).body(medicalTests);
	}

	// update a MedicalTest Status by ID and Patch request
	@PatchMapping("/update/status/{testName}")
	public ResponseEntity<MedicalTest> updateMedicalTestStaus(@Valid @PathVariable String testName,
			@RequestBody MedicalTest updatedMedicalTest) {
		MedicalTest MedicalTest = testServ.updateMedicalTestResult(updatedMedicalTest,testName);
		return ResponseEntity.status(HttpStatus.OK).body(MedicalTest);
	}

	// get an MedicalTest
	@GetMapping("/get/{testName}")
	public ResponseEntity<MedicalTest> getMedicalTest(@PathVariable String testName) {
		MedicalTest medicalTest = testServ.getMedicalTest(testName);
		return ResponseEntity.status(HttpStatus.OK).body(medicalTest);
	}

}
