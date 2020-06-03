package com.kt.springmvc.gestor.model.dto;

import com.kt.springmvc.gestor.model.entity.Subject;
import com.kt.springmvc.gestor.model.entity.User;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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


    public Long getTempSubjectId() {
        return tempSubjectId;
    }

    public void setTempSubjectId(Long tempSubjectId) {
        this.tempSubjectId = tempSubjectId;
    }

    public Set<SubjectDto> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<SubjectDto> subjects) {
        this.subjects = subjects;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateDeleted() {
        return dateDeleted;
    }

    public void setDateDeleted(Date dateDeleted) {
        this.dateDeleted = dateDeleted;
    }

    public Set<User> getStudents() {
        return students;
    }

    public void setStudents(Set<User> students) {
        this.students = students;
    }

    public Long getTempStudentId() {
        return tempStudentId;
    }

    public void setTempStudentId(Long tempStudentId) {
        this.tempStudentId = tempStudentId;
    }
}
