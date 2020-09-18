package com.kt.springmvc.gestor.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Grup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "grups_has_subjects",
            joinColumns = @JoinColumn(name = "grupId"),
            inverseJoinColumns = @JoinColumn(name = "subjectId"))
    private Set<Subject> subjects = new HashSet<>();

    @OneToMany(mappedBy = "studentGrup")
    private Set<User> students = new HashSet<>();

    @NotNull
    private String name;

    private Date dateDeleted;

    public Grup() {
    }
}