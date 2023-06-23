package com.magic.project.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Entity
public class MedicalTest {

	@NotBlank(message = "Test name is required")
	@Id
	private String testName;

	private String testDescription;

	@PositiveOrZero(message = "Test price must be a positive or zero value")
	private double testPrice;

	@Positive(message = "Quantity must be a positive value")
	private int quantity;

	@NotBlank(message = "Test type is required")
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getTestType() {
		return testType;
	}

	public void setTestType(String testType) {
		this.testType = testType;
	}



}
