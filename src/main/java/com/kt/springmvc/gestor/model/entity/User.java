package com.kt.springmvc.gestor.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "user")
    private Set<Grade> grades = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Subject> subjects = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Grup> grups = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "studentGrups_grupId")
    private Grup studentGrup;

    @ManyToMany
    @JoinTable(name = "students_has_parents",
            joinColumns = @JoinColumn(name = "studentId"),
            inverseJoinColumns = @JoinColumn(name = "parentId")
    )
    private Set<User> parents = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "students_has_parents",
            joinColumns = @JoinColumn(name = "parentId"),
            inverseJoinColumns = @JoinColumn(name = "studentId")
    )
    private Set<User> students = new HashSet<>();

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    @Column(unique = true)
    private String email;
    @NotNull

    private String password;

    private Date dateDeleted;
    @NotNull

    private String role;

    private boolean active;

}
