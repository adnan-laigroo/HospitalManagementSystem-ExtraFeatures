package com.magic.project.models;

import javax.persistence.Embeddable;

@Embeddable
public class PrescribedMedicine {

	private int medicineId;

	private String dosage;

	private String frequency;

	public int getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(int medicineId) {
		this.medicineId = medicineId;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
}
