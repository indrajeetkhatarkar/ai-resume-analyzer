package com.indrejeet.airesumeanalyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.indrejeet.airesumeanalyzer.entity.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
}