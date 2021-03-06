package com.kt.springmvc.gestor.repository;


import com.kt.springmvc.gestor.model.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GradeRepository extends JpaRepository<Grade, Long> {

    List<Grade> findByUserIdAndSubjectId(Long userId, Long subjectId);

}
