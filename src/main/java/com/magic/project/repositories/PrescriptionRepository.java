package com.magic.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.magic.project.models.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, String>   {

}
