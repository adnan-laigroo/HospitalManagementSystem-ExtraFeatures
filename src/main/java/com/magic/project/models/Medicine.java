package com.magic.project.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Entity
public class Medicine {
	@Id
	@NotBlank(message = "Medicine name is required")
	private String medicineName;

	@NotBlank(message = "Manufacturer name is required")
	private String manufacturer;

	private String description;

	@PositiveOrZero(message = "Price must be a positive or zero value")
	private double price;

	@Positive(message = "Quantity must be a positive value")
	private int quantity;

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
