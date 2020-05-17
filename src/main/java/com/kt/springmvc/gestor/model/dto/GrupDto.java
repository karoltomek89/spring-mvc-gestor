package com.kt.springmvc.gestor.model.dto;

import java.util.Date;

public class GrupDto {

    private Long id;
    private String name;
    private Integer teachertId;
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

    public Integer getTeachertId() {
        return teachertId;
    }

    public void setTeachertId(Integer teachertId) {
        this.teachertId = teachertId;
    }

    public Date getDateDeleted() {
        return dateDeleted;
    }

    public void setDateDeleted(Date dateDeleted) {
        this.dateDeleted = dateDeleted;
    }

}
