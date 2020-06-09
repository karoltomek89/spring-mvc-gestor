package com.kt.springmvc.gestor.model.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToMany(mappedBy = "subjects")
    private Set<Grup> grups = new HashSet<>();

    @NotNull
    private String name;
    private Date dateDeleted;

    public Subject() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Grup> getGrups() {
        return grups;
    }

    public void setGrups(Set<Grup> grups) {
        this.grups = grups;
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

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", user=" + user +
                ", name='" + name + '\'' +
                ", dateDeleted=" + dateDeleted +
                '}';
    }
}
