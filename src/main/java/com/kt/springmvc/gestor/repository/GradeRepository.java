package com.kt.springmvc.gestor.repository;


import com.kt.springmvc.gestor.model.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {
}
