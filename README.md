# 🤖 AI Resume Analyzer

### AI-powered resume analysis platform built with Spring Boot, Next.js, MySQL and Apache PDFBox.

AI Resume Analyzer is a full-stack web application that allows users to upload PDF resumes, extract resume content, analyze candidate information and technical skills, calculate a resume score, and store resume data in a MySQL database.

The project combines a **Next.js frontend** with a **Java Spring Boot REST API** and a **MySQL database** to provide an end-to-end resume processing workflow.

---

## ✨ Features

* 📄 Upload resumes in PDF format
* 🔍 Extract text from PDF resumes using Apache PDFBox
* 👤 Extract candidate information from resumes
* 🧠 Analyze technical skills from resume content
* 📊 Generate a resume analysis score
* 💾 Store resume information in MySQL
* 🔌 REST APIs for resume management
* 📋 Get all stored resumes
* 🔎 Get a resume by ID
* 🗑️ Delete a resume by ID
* 🧪 Test APIs using Postman
* 🌐 Modern Next.js frontend
* 🔐 Database credentials managed through environment variables

---

## 🏗️ Application Architecture

```text
                    ┌──────────────────────┐
                    │      User / Browser  │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │   Next.js Frontend   │
                    │      Port: 3000      │
                    └──────────┬───────────┘
                               │
                         REST API Calls
                               │
                               ▼
                    ┌──────────────────────┐
                    │ Spring Boot Backend  │
                    │      Port: 8081      │
                    └──────────┬───────────┘
                               │
              ┌────────────────┼────────────────┐
              │                │                │
              ▼                ▼                ▼
       ┌─────────────┐  ┌─────────────┐  ┌─────────────┐
       │ Apache      │  │ Resume      │  │ Spring Data │
       │ PDFBox      │  │ Analysis    │  │ JPA         │
       │             │  │ Service     │  │             │
       └─────────────┘  └─────────────┘  └──────┬──────┘
                                                 │
                                                 ▼
                                      ┌──────────────────┐
                                      │      MySQL       │
                                      │ ai_resume_       │
                                      │ analyzer         │
                                      └──────────────────┘
```

---

## 🔄 Application Workflow

```text
Upload Resume PDF
        │
        ▼
Next.js Frontend
        │
        ▼
Spring Boot REST API
        │
        ▼
PDF Text Extraction
        │
        ▼
Resume Analysis
        │
        ├── Candidate Information
        ├── Technical Skills
        └── Resume Score
        │
        ▼
Store Resume Data
        │
        ▼
MySQL Database
        │
        ▼
Display Analysis Result
```

---

## 🛠️ Tech Stack

### Frontend

* Next.js
* React
* TypeScript
* Tailwind CSS

### Backend

* Java
* Spring Boot
* Spring Web MVC
* Spring Data JPA
* REST APIs
* Apache PDFBox
* Maven

### Database

* MySQL

### Development & Testing

* Git
* GitHub
* Visual Studio Code
* Postman
* MySQL Workbench

---

## 📂 Project Structure

```text
AI-Resume-Analyzer/
│
├── ai-resume-analyzer/                 # Spring Boot Backend
│   │
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── indrejeet/
│   │   │   │           └── airesumeanalyzer/
│   │   │   │               │
│   │   │   │               ├── controller/
│   │   │   │               │   ├── ResumeController.java
│   │   │   │               │   └── ResumeUploadController.java
│   │   │   │               │
│   │   │   │               ├── entity/
│   │   │   │               │   └── Resume.java
│   │   │   │               │
│   │   │   │               ├── repository/
│   │   │   │               │   └── ResumeRepository.java
│   │   │   │               │
│   │   │   │               ├── service/
│   │   │   │               │   ├── PdfTextExtractionService.java
│   │   │   │               │   ├── ResumeAnalysisService.java
│   │   │   │               │   └── ResumeService.java
│   │   │   │               │
│   │   │   │               └── AiResumeAnalyzerApplication.java
│   │   │   │
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   │
│   │   └── test/
│   │
│   ├── pom.xml
│   ├── mvnw
│   └── mvnw.cmd
│
├── ai-resume-frontend/                 # Next.js Frontend
│   │
│   ├── app/
│   ├── public/
│   ├── package.json
│   ├── package-lock.json
│   ├── next.config.ts
│   ├── tsconfig.json
│   └── ...
│
├── .gitignore
└── README.md
```

---

## 🔌 REST API Endpoints

### Create Resume

```http
POST /api/resumes
```

Creates a new resume record.

### Get All Resumes

```http
GET /api/resumes
```

Returns all stored resume records.

### Get Resume By ID

```http
GET /api/resumes/{id}
```

Returns a specific resume using its ID.

### Delete Resume

```http
DELETE /api/resumes/{id}
```

Deletes a resume record.

### Upload Resume PDF

```http
POST /api/resumes/upload
```

Uploads a PDF resume and processes its content.

> API paths may vary depending on the current controller mappings in the project.

---

## 🗄️ Database

The application uses **MySQL** for persistent resume storage.

### Database

```text
ai_resume_analyzer
```

### Database Configuration

Database credentials should be supplied through environment variables rather than committed directly to GitHub.

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ai_resume_analyzer?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=${DB_PASSWORD}
```

Set the password in PowerShell:

```powershell
$env:DB_PASSWORD="YOUR_DATABASE_PASSWORD"
```

---

## ⚙️ Backend Setup

### 1. Clone the Repository

```bash
git clone https://github.com/indrajeetkhatarkar/ai-resume-analyzer.git
```

```bash
cd ai-resume-analyzer
```

### 2. Open the Backend Directory

```bash
cd ai-resume-analyzer
```

### 3. Configure MySQL

Create the database:

```sql
CREATE DATABASE ai_resume_analyzer;
```

Make sure MySQL is running locally.

### 4. Configure Database Password

PowerShell:

```powershell
$env:DB_PASSWORD="YOUR_DATABASE_PASSWORD"
```

### 5. Start Spring Boot

```bash
mvn spring-boot:run
```

Backend will run on:

```text
http://localhost:8081
```

---

## 💻 Frontend Setup

Open another terminal.

Navigate to the frontend:

```bash
cd D:\AI-Resume-Analyzer\ai-resume-frontend
```

Install dependencies:

```bash
npm install
```

Start the development server:

```bash
npm run dev
```

Frontend will be available at:

```text
http://localhost:3000
```

---

## 🧪 API Testing

REST APIs can be tested using **Postman**.

Typical testing flow:

```text
1. Start MySQL
       ↓
2. Start Spring Boot Backend
       ↓
3. Open Postman
       ↓
4. Test Resume APIs
       ↓
5. Upload PDF Resume
       ↓
6. Verify Extracted / Analyzed Data
       ↓
7. Verify Data in MySQL
```

---

## 📊 Resume Analysis

The application processes an uploaded resume through multiple stages:

```text
PDF Resume
    ↓
Text Extraction
    ↓
Candidate Information
    ↓
Skill Detection
    ↓
Resume Analysis
    ↓
Resume Score
    ↓
Database Storage
```

This allows the application to transform an uploaded resume into structured and searchable information.

---

## 🔐 Security

Sensitive configuration should **never be committed to GitHub**.

The project uses environment variables for database credentials:

```text
DB_PASSWORD
```

The actual database password should remain local and should not be included in:

* `application.properties`
* `README.md`
* Git commits
* GitHub repositories
* Screenshots

---

## 🚀 Future Enhancements

Planned improvements can include:

* 🤖 AI/LLM-powered resume recommendations
* 🎯 Job-description matching
* 📈 ATS compatibility analysis
* 💡 Skill-gap recommendations
* 📊 Detailed resume analytics dashboard
* 📄 Support for additional document formats
* 🔐 Authentication and user accounts
* ☁️ Cloud deployment
* 🐳 Docker containerization
* ⚙️ CI/CD pipeline with GitHub Actions

---

## 📌 Project Highlights

This project demonstrates practical experience with:

* Full-stack application development
* Java and Spring Boot
* REST API development
* Spring Data JPA
* MySQL database integration
* PDF processing
* Next.js and TypeScript
* Frontend-backend integration
* Environment-based configuration
* Git and GitHub
* API testing with Postman

---

## 👨‍💻 Author

### Indrajeet Khatarkar

Aspiring Software Engineer focused on **Java, Spring Boot, REST APIs, SQL, AWS and DevOps technologies**.

[![GitHub](https://img.shields.io/badge/GitHub-Indrajeet%20Khatarkar-181717?style=for-the-badge\&logo=github)](https://github.com/indrajeetkhatarkar)

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Indrajeet%20Khatarkar-0A66C2?style=for-the-badge\&logo=linkedin)](https://linkedin.com/in/indrajeet-khatarkar/)

---

## ⭐ Support

If you find this project useful or interesting, consider giving it a ⭐ on GitHub.

---

### 📜 License

This project is created for learning, portfolio and demonstration purposes.
