package com.kt.springmvc.gestor.model.dto;

import com.kt.springmvc.gestor.model.entity.User;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class GrupDto {

    private Long id;
    private String name;
    private Date dateDeleted;
    private Long userId;
    private String userName;
    private String userSurname;
    private Set<SubjectDto> subjects;
    private Long tempSubjectId;
    private Set<User> students;
    private Long tempStudentId;
}
