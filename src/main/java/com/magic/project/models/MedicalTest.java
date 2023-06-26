package com.magic.project.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

@Entity
public class MedicalTest {

	@NotBlank(message = "Test name is required")
	@Id
	private String testName;
	@NotBlank(message = "Test Description is required")
	private String testDescription;
	@NotNull(message = "Test name is required")
	@PositiveOrZero(message = "Test price must be a positive or zero value")
	private double testPrice;

	private String testType;

	// Constructor, getters, and setters

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getTestDescription() {
		return testDescription;
	}

	public void setTestDescription(String testDescription) {
		this.testDescription = testDescription;
	}

	public double getTestPrice() {
		return testPrice;
	}

	public void setTestPrice(double testPrice) {
		this.testPrice = testPrice;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}

}
