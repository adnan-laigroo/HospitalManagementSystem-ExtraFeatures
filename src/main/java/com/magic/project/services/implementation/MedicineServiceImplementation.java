package com.magic.project.services.implementation;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.magic.project.handler.MedicineNotFoundException;
import com.magic.project.models.Medicine;
import com.magic.project.repositories.MedicineRepository;
import com.magic.project.services.MedicineService;

@Service
public class MedicineServiceImplementation implements MedicineService {

	@Autowired
	MedicineRepository medRepo;

	@Override
	public void saveMedicine(@Valid Medicine medicine) {
		medRepo.save(medicine);
	}

	@Override
	public Medicine deleteMedicine(@Valid String medicineName) {
		Medicine Medicine = medRepo.findById(medicineName).orElse(null);
		if (Medicine == null) {
			throw new MedicineNotFoundException("No Medicine with ID " + medicineName);
		}
		medRepo.deleteById(medicineName);
		return Medicine;
	}

	@Override
	public Medicine updateMedicine(@Valid Medicine updatedMedicine, @Valid String medicineName) {
		Medicine Medicine = medRepo.findById(medicineName).orElse(null);
		if (Medicine == null) {
			throw new MedicineNotFoundException("No Medicine with ID " + medicineName);
		}
		updatedMedicine.setMedicineName(medicineName);
		medRepo.save(updatedMedicine);
		return updatedMedicine;
	}

	@Override
	public List<Medicine> getMedicineList() {
		List<Medicine> Medicines = medRepo.findAll();
		if (Medicines.isEmpty()) {
			throw new MedicineNotFoundException("No Medicines.");
		}
		return Medicines;
	}

	@Override
	public Medicine updateMedicineQuantity(@Valid Medicine updatedMedicine, @Valid String medicineName) {
		Medicine medicine = medRepo.findById(medicineName).orElse(null);
		if (medicine == null) {
			throw new MedicineNotFoundException("No Medicine with ID " + medicineName);
		}
		medicine.setQuantity(updatedMedicine.getQuantity());
		medRepo.save(medicine);
		return medicine;
	}

	@Override
	public Medicine getMedicine(String medicineName) {
		Medicine Medicine = medRepo.findById(medicineName).orElse(null);
		if (Medicine == null) {
			throw new MedicineNotFoundException("No Medicine with ID " + medicineName);
		}
		return Medicine;
	}

}
