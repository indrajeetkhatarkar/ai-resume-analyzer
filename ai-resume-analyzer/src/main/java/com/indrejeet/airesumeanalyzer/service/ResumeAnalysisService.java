package com.indrejeet.airesumeanalyzer.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class ResumeAnalysisService {

    private static final List<String> AVAILABLE_SKILLS = Arrays.asList(
            "Java",
            "Spring Boot",
            "Spring",
            "REST APIs",
            "SQL",
            "MySQL",
            "Python",
            "Docker",
            "Kubernetes",
            "AWS",
            "Git",
            "GitHub",
            "Jenkins",
            "Maven",
            "Linux",
            "HTML",
            "CSS",
            "JavaScript",
            "React",
            "Node.js"
    );

    public List<String> extractSkills(String resumeText) {

        List<String> detectedSkills = new ArrayList<>();

        if (resumeText == null || resumeText.isBlank()) {
            return detectedSkills;
        }

        String text = resumeText.toLowerCase();

        for (String skill : AVAILABLE_SKILLS) {

            if (text.contains(skill.toLowerCase())) {
                detectedSkills.add(skill);
            }
        }

        return detectedSkills;
    }

    public String extractCandidateName(String resumeText) {

        if (resumeText == null || resumeText.isBlank()) {
            return "Unknown";
        }

        String[] lines = resumeText.split("\\r?\\n");

        for (String line : lines) {

            String cleanedLine = line.trim();

            if (!cleanedLine.isBlank()
                    && !cleanedLine.contains("@")
                    && !cleanedLine.matches(".*\\d.*")
                    && !cleanedLine.equalsIgnoreCase("PROFESSIONAL SUMMARY")
                    && !cleanedLine.equalsIgnoreCase("TECHNICAL SKILLS")
                    && !cleanedLine.equalsIgnoreCase("EXPERIENCE")
                    && !cleanedLine.equalsIgnoreCase("PROJECT")
                    && !cleanedLine.equalsIgnoreCase("EDUCATION")
                    && !cleanedLine.equalsIgnoreCase("CERTIFICATIONS")) {

                return cleanedLine;
            }
        }

        return "Unknown";
    }

    public String extractEmail(String resumeText) {

        if (resumeText == null || resumeText.isBlank()) {
            return "Unknown";
        }

        Pattern emailPattern = Pattern.compile(
                "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}"
        );

        Matcher matcher = emailPattern.matcher(resumeText);

        if (matcher.find()) {
            return matcher.group();
        }

        return "Unknown";
    }

    public String extractEducation(String resumeText) {

        if (resumeText == null || resumeText.isBlank()) {
            return "Unknown";
        }

        String[] lines = resumeText.split("\\r?\\n");

        boolean educationSection = false;
        StringBuilder education = new StringBuilder();

        for (String line : lines) {

            String cleanedLine = line.trim();

            if (cleanedLine.equalsIgnoreCase("EDUCATION")) {
                educationSection = true;
                continue;
            }

            if (educationSection) {

                if (cleanedLine.equalsIgnoreCase("CERTIFICATIONS")
                        || cleanedLine.equalsIgnoreCase("PROJECT")
                        || cleanedLine.equalsIgnoreCase("EXPERIENCE")) {
                    break;
                }

                if (!cleanedLine.isBlank()) {
                    education.append(cleanedLine).append(" ");
                }
            }
        }

        if (education.length() == 0) {
            return "Unknown";
        }

        return education.toString().trim();
    }

    public String extractExperience(String resumeText) {

        if (resumeText == null || resumeText.isBlank()) {
            return "Unknown";
        }

        String[] lines = resumeText.split("\\r?\\n");

        boolean experienceSection = false;
        StringBuilder experience = new StringBuilder();

        for (String line : lines) {

            String cleanedLine = line.trim();

            if (cleanedLine.equalsIgnoreCase("EXPERIENCE")) {
                experienceSection = true;
                continue;
            }

            if (experienceSection) {

                if (cleanedLine.equalsIgnoreCase("PROJECT")
                        || cleanedLine.equalsIgnoreCase("CERTIFICATIONS")
                        || cleanedLine.equalsIgnoreCase("EDUCATION")) {
                    break;
                }

                if (!cleanedLine.isBlank()) {
                    experience.append(cleanedLine).append(" ");
                }
            }
        }

        if (experience.length() == 0) {
            return "Unknown";
        }

        return experience.toString().trim();
    }

    public double calculateScore(String resumeText) {

        List<String> detectedSkills = extractSkills(resumeText);

        if (detectedSkills.isEmpty()) {
            return 0.0;
        }

        double score = (detectedSkills.size() * 100.0)
                / AVAILABLE_SKILLS.size();

        return Math.min(score, 100.0);
    }
}