# AI Resume Analyzer

A Spring Boot based REST API project that allows resume data to be created, retrieved, deleted, and resumes to be uploaded for PDF text extraction and analysis.

## 🚀 Features

- Create resume records using REST API
- Get all resumes
- Get resume by ID
- Delete resume by ID
- Upload resume PDF using multipart/form-data
- Extract text from uploaded PDF
- Store resume information in MySQL database
- Test APIs using Postman
- Maven-based project build

## 🛠️ Tech Stack

- Java
- Spring Boot
- Spring Web MVC
- Spring Data JPA
- MySQL
- Maven
- REST API
- Postman
- PDF text extraction

## 📁 Project Structure

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
└── .gitignore
