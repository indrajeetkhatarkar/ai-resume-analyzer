package com.indrejeet.airesumeanalyzer.service;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

@Service
public class PdfTextExtractionService {

    public String extractText(String fileName) throws IOException {

        Path filePath = Paths.get("uploads").resolve(fileName);

        try (PDDocument document = Loader.loadPDF(filePath.toFile())) {

            PDFTextStripper pdfTextStripper = new PDFTextStripper();

            return pdfTextStripper.getText(document);
        }
    }
}