package com.example.converter.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class PdfToExcelService {

    public static void convert(String in, String out) throws Exception {

        File input = new File(in);
        if (!input.exists()) {
            throw new Exception("Fichier PDF introuvable : " + in);
        }

        System.out.println("PDFBox → POI : PDF vers Excel");
        System.out.println("Entrée : " + input.getAbsolutePath());
        System.out.println("Sortie : " + out);

        // 🔹 1) Lire le texte du PDF
        PDDocument document = PDDocument.load(input);
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(document);
        document.close();

        // 🔹 2) Découper en lignes
        String[] lines = text.split("\\r?\\n");

        // 🔹 3) Créer le fichier Excel
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("PDF");

        int rowIndex = 0;

        for (String line : lines) {
            Row row = sheet.createRow(rowIndex++);

            // Découpe simple par colonnes (espaces multiples ou tab)
            String[] cells = line.trim().split("\\s{2,}|\t");

            int colIndex = 0;
            for (String cell : cells) {
                Cell excelCell = row.createCell(colIndex++);
                excelCell.setCellValue(cell);
            }
        }

        // 🔹 4) Sauvegarde Excel
        try (FileOutputStream fos = new FileOutputStream(out)) {
            workbook.write(fos);
        }

        workbook.close();

        File output = new File(out);
        System.out.println("Excel créé ? " + output.exists());
    }
}