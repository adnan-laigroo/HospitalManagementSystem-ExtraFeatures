package com.magic.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.magic.project.models.MedicalTest;

public interface MedicalTestRepository extends JpaRepository<MedicalTest, String>   {

}
