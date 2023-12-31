package com.magic.project.controller;

import com.magic.project.models.Doctor;
import com.magic.project.models.User;
import com.magic.project.models.dto.DoctorDto;
import com.magic.project.models.dto.DoctorUserMapper;
import com.magic.project.services.DoctorService;
import com.magic.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("hospital/doctor")
public class DoctorController {
	@Autowired
	DoctorService docServ;
	@Autowired
	UserService userServ;

	// Add an doctor
	@PostMapping("/add")
	public ResponseEntity<Doctor> addDoctor(@Valid @RequestBody DoctorDto doctorDto) {
		Doctor doctor = DoctorUserMapper.mapToDoctor(doctorDto);
		User user = DoctorUserMapper.mapToUser(doctorDto);
		docServ.saveDoctor(doctor);
		user.setUsername(doctor.getEmail());
		userServ.saveUser(user);
		return ResponseEntity.status(HttpStatus.OK).body(doctor);
	}

	// delete a doctor
	@DeleteMapping("/delete/{email}")
	public ResponseEntity<Doctor> deleteDoctor(@Valid @PathVariable String email) {
		Doctor doctor = docServ.deleteDoctor(email);
		return ResponseEntity.status(HttpStatus.OK).body(doctor);
	}

	// update a doctor by ID and Put request
	@PutMapping("/update/{email}")
	public ResponseEntity<Doctor> updateDoctor(@Valid @PathVariable String email, @RequestBody @Valid Doctor updatedDoctor) {
		Doctor doctor = docServ.updateDoctor(updatedDoctor, email);
		return ResponseEntity.status(HttpStatus.OK).body(doctor);
	}

	// get list of all doctors
	@GetMapping("/list")
	public ResponseEntity<List<Doctor>> getAllDoctor() {
		List<Doctor> doctors = docServ.getDoctorList();
		return ResponseEntity.status(HttpStatus.OK).body(doctors);
	}
	@GetMapping("/get/{email}")
	public ResponseEntity<Doctor> getDoctor(@PathVariable @Valid String email) {
		Doctor doctor = docServ.getDoctor(email);
		return ResponseEntity.status(HttpStatus.OK).body(doctor);
	}

}