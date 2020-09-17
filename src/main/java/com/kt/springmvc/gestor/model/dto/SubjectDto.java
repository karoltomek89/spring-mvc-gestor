package com.kt.springmvc.gestor.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class SubjectDto {

    private Long id;
    private String name;
    private Long userId;
    private Date dateDeleted;
    private String userName;
    private String userSurname;

}
