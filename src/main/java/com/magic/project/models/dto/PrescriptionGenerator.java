package com.magic.project.models.dto;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
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

             // Set font styles
             Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, BaseColor.BLACK);
             Font headingFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK);
             Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 10, BaseColor.BLACK);

             // Add prescription details
             Paragraph title = new Paragraph("Prescription Details", titleFont);
             title.setAlignment(Element.ALIGN_CENTER);
             document.add(title);

             document.add(new Paragraph("AP ID: " + prescription.getApId(), headingFont));
             document.add(new Paragraph("Patient Name: " + prescription.getPatientName(), headingFont));
             document.add(new Paragraph("Doctor Name: " + prescription.getDoctorName(), headingFont));
             document.add(new Paragraph("Symptom: " + prescription.getSymptom(), headingFont));
             document.add(new Paragraph("Diagnosis: " + prescription.getDiagnosis(), headingFont));

             // Add prescribed medicines
             document.add(new Paragraph("Prescribed Medicines:", headingFont));
             PdfPTable medicineTable = new PdfPTable(3);
             medicineTable.setWidthPercentage(100);

             PdfPCell cell = new PdfPCell(new Phrase("Medicine Name", headingFont));
             medicineTable.addCell(cell);
             cell = new PdfPCell(new Phrase("Dosage", headingFont));
             medicineTable.addCell(cell);
             cell = new PdfPCell(new Phrase("Frequency", headingFont));
             medicineTable.addCell(cell);

             int medicineIndex = 1;
             for (PrescribedMedicine medicine : prescription.getPrescribedMedicines()) {
                 cell = new PdfPCell(new Phrase(medicine.getMedicineName(), normalFont));
                 medicineTable.addCell(cell);
                 cell = new PdfPCell(new Phrase(medicine.getDosage(), normalFont));
                 medicineTable.addCell(cell);
                 cell = new PdfPCell(new Phrase(medicine.getFrequency(), normalFont));
                 medicineTable.addCell(cell);
                 medicineIndex++;
             }

             document.add(medicineTable);

             // Add prescribed tests
             document.add(new Paragraph("Prescribed Tests:", headingFont));
             PdfPTable testTable = new PdfPTable(2);
             testTable.setWidthPercentage(100);

             cell = new PdfPCell(new Phrase("Test Name", headingFont));
             testTable.addCell(cell);
             cell = new PdfPCell(new Phrase("Test Result", headingFont));
             testTable.addCell(cell);

             int testIndex = 1;
             for (PrescribedTest test : prescription.getPrescribedTests()) {
                 cell = new PdfPCell(new Phrase(test.getTestName(), normalFont));
                 testTable.addCell(cell);
                 cell = new PdfPCell(new Phrase(test.getTestResult(), normalFont));
                 testTable.addCell(cell);
                 testIndex++;
             }

             document.add(testTable);

             // Add notes
             document.add(new Paragraph("Notes: " + prescription.getNotes(), normalFont));

             document.close();
         } catch (DocumentException e) {
             throw new DocumentException("Error generating prescription PDF: " + e.getMessage());
         }

         return outputStream.toByteArray();
     }

}

