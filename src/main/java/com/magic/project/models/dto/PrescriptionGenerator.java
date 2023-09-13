package com.magic.project.models.dto;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.magic.project.models.PrescribedMedicine;
import com.magic.project.models.PrescribedTest;
import com.magic.project.models.Prescription;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PrescriptionGenerator {

    public static byte[] generatePrescriptionPDF(Prescription prescription) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            document.open();

            // Set font styles
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, BaseColor.BLACK);
            Font headingFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.BLACK);
            Font normalFont = FontFactory.getFont(FontFactory.HELVETICA, 12, BaseColor.BLACK);

            // Add prescription details
            Paragraph title = new Paragraph("PRESCRIPTION", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

            addLineSeparator(document);

            document.add(new Paragraph("AP ID: " + prescription.getApId(), headingFont));
            document.add(new Paragraph("Patient Name: " + prescription.getPatientName(), headingFont));
            document.add(new Paragraph("Doctor Name: " + prescription.getDoctorName(), headingFont));

            addLineSeparator(document);

            document.add(new Paragraph("SYMPTOMS", headingFont));
            document.add(new Paragraph(prescription.getSymptom(), normalFont));

            addLineSeparator(document);

            document.add(new Paragraph("DIAGNOSIS", headingFont));
            document.add(new Paragraph(prescription.getDiagnosis(), normalFont));

            addLineSeparator(document);

            // Add prescribed medicines
            document.add(new Paragraph("PRESCRIBED MEDICINES", headingFont));

            document.add(Chunk.NEWLINE);
            PdfPTable medicineTable = new PdfPTable(3);
            medicineTable.setWidthPercentage(100);

            addTableHeaderCell(medicineTable, "Medicine Name", headingFont);
            addTableHeaderCell(medicineTable, "Dosage", headingFont);
            addTableHeaderCell(medicineTable, "Frequency", headingFont);

            for (PrescribedMedicine medicine : prescription.getPrescribedMedicines()) {
                addTableCell(medicineTable, medicine.getMedicineName(), normalFont);
                addTableCell(medicineTable, medicine.getDosage(), normalFont);
                addTableCell(medicineTable, medicine.getFrequency(), normalFont);
            }

            document.add(medicineTable);

            addLineSeparator(document);

            // Add prescribed tests
            document.add(new Paragraph("PRESCRIBED TESTS", headingFont));

            document.add(Chunk.NEWLINE);
            PdfPTable testTable = new PdfPTable(2);
            testTable.setWidthPercentage(100);

            addTableHeaderCell(testTable, "Test Name", headingFont);
            addTableHeaderCell(testTable, "Test Result", headingFont);

            for (PrescribedTest test : prescription.getPrescribedTests()) {
                addTableCell(testTable, test.getTestName(), normalFont);
                addTableCell(testTable, test.getTestResult(), normalFont);
            }

            document.add(testTable);

            addLineSeparator(document);

            // Add notes
            document.add(new Paragraph("NOTES", headingFont));
            document.add(new Paragraph(prescription.getNotes(), normalFont));

            
            addLineSeparator(document);
            // Add doctor's stamp and date
            Paragraph stamp = new Paragraph();
            stamp.setAlignment(Element.ALIGN_RIGHT);
            stamp.setSpacingBefore(50);

            Font stampFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.BLACK);
            Chunk stampText = new Chunk("Doctor's Stamp", stampFont);
            Chunk dateText = new Chunk("Date: " + getCurrentDate(), stampFont);

            stamp.add(stampText);
            stamp.add(Chunk.NEWLINE);
            stamp.add(dateText);

            document.add(stamp);

            document.close();
        } catch (DocumentException e) {
            throw new DocumentException("Error generating prescription PDF: " + e.getMessage());
        }

        return outputStream.toByteArray();
    }

    private static String getCurrentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date());
    }

    private static void addLineSeparator(Document document) throws DocumentException {
        LineSeparator lineSeparator = new LineSeparator();
        lineSeparator.setLineColor(BaseColor.GRAY);
        document.add(new Chunk(lineSeparator));
    }

    private static void addTableHeaderCell(PdfPTable table, String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
    }

    private static void addTableCell(PdfPTable table, String text, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
    }
}
