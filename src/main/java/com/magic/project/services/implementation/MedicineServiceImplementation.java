package com.magic.project.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;

import com.magic.project.repositories.MedicineRepository;
import com.magic.project.services.MedicineService;

public class MedicineServiceImplementation implements MedicineService {

	@Autowired
	MedicineRepository medRepo;

}
