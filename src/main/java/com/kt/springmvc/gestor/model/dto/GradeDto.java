package com.kt.springmvc.gestor.model.dto;

import lombok.Data;

@Data
public class GradeDto {

    private Long id;
    private Long subjectId;
    private Long studentId;
    private Long value;

}