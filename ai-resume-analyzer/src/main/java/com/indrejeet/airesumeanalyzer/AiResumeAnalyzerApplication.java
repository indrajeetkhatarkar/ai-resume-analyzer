package com.indrejeet.airesumeanalyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "http://localhost:3000")
public class AiResumeAnalyzerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiResumeAnalyzerApplication.class, args);
    }

}