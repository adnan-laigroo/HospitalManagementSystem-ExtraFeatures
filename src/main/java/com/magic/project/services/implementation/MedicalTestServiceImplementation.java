package com.magic.project.services.implementation;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import com.magic.project.repositories.MedicalTestRepository;
import com.magic.project.services.MedicalTestService;

public class MedicalTestServiceImplementation implements MedicalTestService {

	@Autowired
	MedicalTestRepository testRepo;


	
	
	
	
	
	
	
	
	
	
	
	private static Map<String, String> testNameToTypeMap = new HashMap<>();
	// Add mappings
	static {
		// Blood Tests
		testNameToTypeMap.put("Complete Blood Count (CBC)", "Blood Test"); // Measures various components of blood
		testNameToTypeMap.put("Blood Chemistry Panel", "Blood Test"); // Checks blood chemistry levels
		testNameToTypeMap.put("Lipid Profile", "Blood Test"); // Measures cholesterol and triglyceride levels
		testNameToTypeMap.put("Blood Glucose Test", "Blood Test"); // Checks blood sugar levels
		testNameToTypeMap.put("Coagulation Profile", "Blood Test"); // Evaluates blood clotting ability
		testNameToTypeMap.put("Liver Function Test", "Blood Test"); // Assesses liver health and function
		testNameToTypeMap.put("Kidney Function Test", "Blood Test"); // Measures kidney function and health
		testNameToTypeMap.put("HbA1c Test", "Blood Test"); // Provides information about average blood sugar levels over
															// time

		// Imaging Tests
		testNameToTypeMap.put("X-ray", "Imaging Test"); // Uses electromagnetic radiation to create images of the body
		testNameToTypeMap.put("Magnetic Resonance Imaging (MRI)", "Imaging Test"); // Uses strong magnetic fields and
																					// radio waves to produce detailed
																					// images
		testNameToTypeMap.put("Computed Tomography (CT) Scan", "Imaging Test"); // Combines X-ray images from different
																				// angles to create cross-sectional
																				// views
		testNameToTypeMap.put("Ultrasound", "Imaging Test"); // Uses sound waves to generate images of organs and
																// tissues
		testNameToTypeMap.put("Mammography", "Imaging Test"); // Special X-ray of the breasts for breast cancer
																// screening
		testNameToTypeMap.put("Bone Density Test (DXA Scan)", "Imaging Test"); // Measures bone mineral density to
																				// assess bone health

		// Genetic Tests
		testNameToTypeMap.put("Carrier Testing", "Genetic Test"); // Identifies if an individual carries a genetic
																	// mutation
		testNameToTypeMap.put("Prenatal Genetic Testing", "Genetic Test"); // Screens for genetic disorders during
																			// pregnancy
		testNameToTypeMap.put("BRCA Genetic Testing", "Genetic Test"); // Identifies mutations in BRCA1 and BRCA2 genes
																		// related to breast and ovarian cancer risk
		testNameToTypeMap.put("Pharmacogenetic Testing", "Genetic Test"); // Evaluates how genes may affect response to
																			// certain medications
		testNameToTypeMap.put("Paternity Testing", "Genetic Test"); // Determines biological relationship between
																	// individuals

		// Pathology Tests
		testNameToTypeMap.put("Pathology Test", "Pathology Test"); // General term for tests involving the examination
																	// of tissues, fluids, or cells
		testNameToTypeMap.put("Biopsy Test", "Pathology Test"); // Extracts and examines a sample of tissue for
																// diagnostic purposes
		testNameToTypeMap.put("Pap Smear Test", "Pathology Test"); // Screens for cervical cancer and detects
																	// abnormalities in cervical cells
		testNameToTypeMap.put("Stool Test", "Pathology Test"); // Analyzes stool samples for signs of diseases or
																// infections

		// Diagnostic Tests
		testNameToTypeMap.put("Electrocardiogram (ECG) Test", "Diagnostic Test"); // Records the electrical activity of
																					// the heart
		testNameToTypeMap.put("Endoscopy Test", "Diagnostic Test"); // Uses a flexible tube with a camera to visualize
																	// and examine internal organs
		testNameToTypeMap.put("Pulmonary Function Test (PFT)", "Diagnostic Test"); // Measures lung function and
																					// capacity
		testNameToTypeMap.put("Colonoscopy Test", "Diagnostic Test"); // Examines the colon and rectum for abnormalities

		// Other Tests
		testNameToTypeMap.put("Allergy Test", "Allergy Test"); // Identifies allergens causing an allergic reaction
		testNameToTypeMap.put("Pregnancy Test", "Pregnancy Test"); // Determines if a person is pregnant

		// Additional Tests
		testNameToTypeMap.put("HIV Test", "Sexually Transmitted Infection Test"); // Detects the presence of HIV
																					// antibodies or antigens
		testNameToTypeMap.put("Diabetes Test", "Diabetes Test"); // Tests for diabetes or measures blood glucose levels
		testNameToTypeMap.put("Cholesterol Test", "Cardiovascular Test"); // Assesses cholesterol levels for
																			// cardiovascular health
	}
}
