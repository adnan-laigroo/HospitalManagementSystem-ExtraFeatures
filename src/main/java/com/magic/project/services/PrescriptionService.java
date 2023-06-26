package com.magic.project.services;

import java.util.List;

import javax.validation.Valid;

import com.itextpdf.text.DocumentException;
import com.magic.project.models.Prescription;

public interface PrescriptionService {

	void savePrescription(@Valid Prescription prescription);

	Prescription deletePrescription(@Valid String appId);

	Prescription updatePrescription(@Valid Prescription updatedPrescription, @Valid String appId);

	List<Prescription> getPrescriptionList();

	Prescription getPrescription(String appId);

	Prescription updatePrescriptionTestResults(@Valid Prescription updatedPrescription, @Valid String appId);

	byte[] generatePrescriptionPDF(String appId) throws DocumentException;

}
