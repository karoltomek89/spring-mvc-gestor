package com.kt.springmvc.gestor.repository;

import com.kt.springmvc.gestor.model.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findByUserId(Long id);
}
