package com.indrejeet.airesumeanalyzer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.indrejeet.airesumeanalyzer.entity.Resume;
import com.indrejeet.airesumeanalyzer.repository.ResumeRepository;

@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;

    public ResumeService(ResumeRepository resumeRepository) {
        this.resumeRepository = resumeRepository;
    }

    public Resume saveResume(Resume resume) {
        return resumeRepository.save(resume);
    }

    public List<Resume> getAllResumes() {
        return resumeRepository.findAll();
    }

    public Optional<Resume> getResumeById(Long id) {
        return resumeRepository.findById(id);
    }

    public void deleteResume(Long id) {
        resumeRepository.deleteById(id);
    }
}