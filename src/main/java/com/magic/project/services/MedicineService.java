package com.magic.project.services;

import java.util.List;

import javax.validation.Valid;

import com.magic.project.models.Medicine;

public interface MedicineService {

	void saveMedicine(@Valid Medicine medicine);

	Medicine deleteMedicine(@Valid String medicineName);

	Medicine updateMedicine(@Valid Medicine updatedmedicine, @Valid String medicineName);

	List<Medicine> getMedicineList();

	Medicine updateMedicineQuantity(Medicine updatedmedicine, @Valid String medicineName);

	Medicine getMedicine(String medicineName);

}
