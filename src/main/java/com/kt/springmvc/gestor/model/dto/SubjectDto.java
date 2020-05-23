package com.kt.springmvc.gestor.model.dto;

import com.kt.springmvc.gestor.model.entity.User;

import java.util.Date;

public class SubjectDto {

    private Long id;
    private String name;
    private User user;
    private Date dateDeleted;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDateDeleted() {
        return dateDeleted;
    }

    public void setDateDeleted(Date dateDeleted) {
        this.dateDeleted = dateDeleted;
    }
}
