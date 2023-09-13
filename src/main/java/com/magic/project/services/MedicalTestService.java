package com.magic.project.services;

import java.util.List;

import javax.validation.Valid;

import com.magic.project.models.MedicalTest;

public interface MedicalTestService {

	void saveMedicalTest(@Valid MedicalTest medicalTest);

	MedicalTest deleteMedicalTest(@Valid String testName);

	MedicalTest updateMedicalTest(@Valid MedicalTest updatedMedicalTest, @Valid String testName);

	List<MedicalTest> getMedicalTestList();

	MedicalTest updateMedicalTestPrice(MedicalTest updatedMedicalTest, @Valid String testName);

	MedicalTest getMedicalTest(String testName);

}
