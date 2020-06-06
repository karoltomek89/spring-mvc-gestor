package com.kt.springmvc.gestor.model.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Grup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(
            name = "grups_has_subjects",
            joinColumns = @JoinColumn(name = "grupId"),
            inverseJoinColumns = @JoinColumn(name = "subjectId"))
    private Set<Subject> subjects = new HashSet();

    @OneToMany(mappedBy = "studentGrup")
    private Set<User> students = new HashSet();

    private String name;
    private Date dateDeleted;

    public Grup() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
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

    public void addStudentToGrup(User student) {
        students.add(student);
    }

    public void deleteStudentFromGrup(User student) {
        students.remove(student);
    }

    @Override
    public String toString() {
        return "Grup{" +
                "id=" + id +
                ", user=" + user +
                ", subjects=" + subjects +
                ", students=" + students +
                ", name='" + name + '\'' +
                ", dateDeleted=" + dateDeleted +
                '}';
    }
}