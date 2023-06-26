package com.magic.project.models.dto;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.magic.project.models.PrescribedMedicine;
import com.magic.project.models.PrescribedTest;
import com.magic.project.models.Prescription;

import java.io.ByteArrayOutputStream;

public class PrescriptionGenerator {

    public static byte[] generatePrescriptionPDF(Prescription prescription) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, outputStream);
            document.open();

            // Add prescription details
            document.add(new Paragraph("Prescription Details"));
            document.add(new Paragraph("AP ID: " + prescription.getApId()));
            document.add(new Paragraph("Patient Name: " + prescription.getPatientName()));
            document.add(new Paragraph("Doctor Name: " + prescription.getDoctorName()));
            document.add(new Paragraph("Symptom: " + prescription.getSymptom()));
            document.add(new Paragraph("Diagnosis: " + prescription.getDiagnosis()));

            // Add prescribed medicines
            document.add(new Paragraph("Prescribed Medicines:"));
            for (PrescribedMedicine medicine : prescription.getPrescribedMedicines()) {
                document.add(new Paragraph("Medicine Name: " + medicine.getMedicineName()));
                document.add(new Paragraph("Dosage: " + medicine.getDosage()));
                document.add(new Paragraph("Frequency: " + medicine.getFrequency()));
                document.add(new Paragraph("----------------------"));
            }

            // Add prescribed tests
            document.add(new Paragraph("Prescribed Tests:"));
            for (PrescribedTest test : prescription.getPrescribedTests()) {
                document.add(new Paragraph("Test Name: " + test.getTestName()));
                document.add(new Paragraph("Test Result: " + test.getTestResult()));
                document.add(new Paragraph("----------------------"));
            }

            // Add notes
            document.add(new Paragraph("Notes: " + prescription.getNotes()));

            document.close();
        } catch (DocumentException e) {
            throw new DocumentException("Error generating prescription PDF: " + e.getMessage());
        }

        return outputStream.toByteArray();
    }
}

