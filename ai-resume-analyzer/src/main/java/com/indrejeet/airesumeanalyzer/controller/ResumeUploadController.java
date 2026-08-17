package com.indrejeet.airesumeanalyzer.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.indrejeet.airesumeanalyzer.entity.Resume;
import com.indrejeet.airesumeanalyzer.service.PdfTextExtractionService;
import com.indrejeet.airesumeanalyzer.service.ResumeAnalysisService;
import com.indrejeet.airesumeanalyzer.service.ResumeService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/resumes")
public class ResumeUploadController {

    private final PdfTextExtractionService pdfTextExtractionService;
    private final ResumeAnalysisService resumeAnalysisService;
    private final ResumeService resumeService;

    private final String uploadDir = "uploads/";

    public ResumeUploadController(
            PdfTextExtractionService pdfTextExtractionService,
            ResumeAnalysisService resumeAnalysisService,
            ResumeService resumeService) {

        this.pdfTextExtractionService = pdfTextExtractionService;
        this.resumeAnalysisService = resumeAnalysisService;
        this.resumeService = resumeService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadResume(
            @RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest()
                    .body("Please select a file to upload.");
        }

        try {

            // Create upload directory if it does not exist
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Get uploaded file name
            String fileName = file.getOriginalFilename();

            // Create file path
            Path filePath = uploadPath.resolve(fileName);

            // Save uploaded PDF
            Files.copy(
                    file.getInputStream(),
                    filePath,
                    StandardCopyOption.REPLACE_EXISTING
            );

            // Extract text from PDF
            String extractedText =
                    pdfTextExtractionService.extractText(fileName);

            // Detect skills
            var detectedSkills =
                    resumeAnalysisService.extractSkills(extractedText);

            // Calculate resume score
            double score =
                    resumeAnalysisService.calculateScore(extractedText);

            // Extract candidate name
            String candidateName =
                    resumeAnalysisService.extractCandidateName(extractedText);

            // Extract email
            String email =
                    resumeAnalysisService.extractEmail(extractedText);

            // Extract education
            String education =
                    resumeAnalysisService.extractEducation(extractedText);

            // Extract experience
            String experience =
                    resumeAnalysisService.extractExperience(extractedText);

            // Create Resume object
            Resume resume = new Resume();

            resume.setCandidateName(candidateName);
            resume.setEmail(email);
            resume.setResumeText(extractedText);
            resume.setSkills(String.join(", ", detectedSkills));
            resume.setEducation(education);
            resume.setExperience(experience);
            resume.setScore(score);

            // Save resume in MySQL
            resumeService.saveResume(resume);

            // Prepare response
            String response = """
                    Resume Analysis Result

                    Candidate Name:
                    %s

                    Email:
                    %s

                    Education:
                    %s

                    Experience:
                    %s

                    Detected Skills:
                    %s

                    Resume Score:
                    %.2f/100

                    Resume saved successfully in database.
                    """.formatted(
                            candidateName,
                            email,
                            education,
                            experience,
                            detectedSkills,
                            score
                    );

            return ResponseEntity.ok(response);

        } catch (IOException e) {

            return ResponseEntity.internalServerError()
                    .body("Failed to process resume: " + e.getMessage());
        }
    }
}