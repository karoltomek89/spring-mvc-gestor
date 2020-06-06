package com.kt.springmvc.gestor.model.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "user")
    private Set<Grade> grades;

    @OneToMany(mappedBy = "user")
    private Set<Subject> subjects;

    @OneToMany(mappedBy = "user")
    private Set<Grup> grups;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "studentGrups_grupId")
    private Grup studentGrup;

    @ManyToMany
    @JoinTable(name = "students_has_parents",
            joinColumns = @JoinColumn(name = "studentId"),
            inverseJoinColumns = @JoinColumn(name = "parentId")
    )
    private Set<User> parents = new HashSet();

    @ManyToMany
    @JoinTable(name = "students_has_parents",
            joinColumns = @JoinColumn(name = "parentId"),
            inverseJoinColumns = @JoinColumn(name = "studentId")
    )
    private Set<User> students = new HashSet();

    private String name;
    private String surname;
    private String email;
    private String password;
    private Date dateDeleted;
    private String role;
    private boolean active;

    public User() {
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateDeleted() {
        return dateDeleted;
    }

    public void setDateDeleted(Date dateDeleted) {
        this.dateDeleted = dateDeleted;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Grade> getGrades() {
        return grades;
    }

    public void setGrades(Set<Grade> grades) {
        this.grades = grades;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public Set<Grup> getGrups() {
        return grups;
    }

    public void setGrups(Set<Grup> grups) {
        this.grups = grups;
    }

    public Grup getStudentGrup() {
        return studentGrup;
    }

    public void setStudentGrup(Grup grup) {
        this.studentGrup = grup;
    }

    public Set<User> getParents() {
        return parents;
    }

    public void setParents(Set<User> parents) {
        this.parents = parents;
    }

    public Set<User> getStudents() {
        return students;
    }

    public void setStudents(Set<User> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", grades=" + grades +
                ", subjects=" + subjects +
                ", parents=" + parents +
                ", students=" + students +
                ", grups=" + grups +
                ", studentGrup=" + studentGrup +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", dateDeleted=" + dateDeleted +
                ", role='" + role + '\'' +
                ", active=" + active +
                '}';
    }
}
