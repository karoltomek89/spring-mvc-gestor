package com.kt.springmvc.gestor.repository;


import com.kt.springmvc.gestor.model.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
