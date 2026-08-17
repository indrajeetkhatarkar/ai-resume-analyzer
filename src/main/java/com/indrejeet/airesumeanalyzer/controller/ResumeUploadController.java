package com.indrejeet.airesumeanalyzer.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.indrejeet.airesumeanalyzer.service.PdfTextExtractionService;

@RestController
@RequestMapping("/api/resumes")
public class ResumeUploadController {

    private final PdfTextExtractionService pdfTextExtractionService;

    private final String uploadDir = "uploads/";

    public ResumeUploadController(PdfTextExtractionService pdfTextExtractionService) {
        this.pdfTextExtractionService = pdfTextExtractionService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadResume(
            @RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("Please select a file to upload.");
        }

        try {
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileName = file.getOriginalFilename();

            Path filePath = uploadPath.resolve(fileName);

            Files.copy(
                    file.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING
            );

            String extractedText =
                    pdfTextExtractionService.extractText(fileName);

            return ResponseEntity.ok(extractedText);

        } catch (IOException e) {
            return ResponseEntity.internalServerError()
                    .body("Failed to process resume: " + e.getMessage());
        }
    }
}