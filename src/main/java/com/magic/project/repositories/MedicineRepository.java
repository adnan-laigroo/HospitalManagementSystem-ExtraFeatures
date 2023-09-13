package com.magic.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magic.project.models.Medicine;

public interface MedicineRepository extends JpaRepository<Medicine, String>  {

}
