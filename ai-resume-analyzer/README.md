# AI Resume Analyzer

A Java Spring Boot based backend application for managing and processing resume data through REST APIs.

## 🚀 Features

- Create resume records using REST APIs
- Get all resumes
- Get a resume by ID
- Delete a resume by ID
- Upload resume PDF files
- Extract text from uploaded PDF resumes
- Store resume information in MySQL
- Test REST APIs using Postman

## 🛠️ Tech Stack

- Java
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- MySQL
- Maven
- REST APIs
- Apache PDFBox
- Postman

## 📂 Project Structure

```text
ai-resume-analyzer/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/indrejeet/airesumeanalyzer/
│   │   │       ├── controller/
│   │   │       │   ├── ResumeController.java
│   │   │       │   └── ResumeUploadController.java
│   │   │       ├── entity/
│   │   │       │   └── Resume.java
│   │   │       ├── repository/
│   │   │       │   └── ResumeRepository.java
│   │   │       ├── service/
│   │   │       │   ├── PdfTextExtractionService.java
│   │   │       │   └── ResumeService.java
│   │   │       └── AiResumeAnalyzerApplication.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
├── mvnw
├── mvnw.cmd
├── .gitignore
└── README.md