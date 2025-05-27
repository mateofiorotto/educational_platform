package com.mateo.plataforma_educativa.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Table(name = "courses")
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name="teacher_id", nullable = false)
    private Teacher teacher;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();

    public Course(){}

    public Course(Long id, String name, Teacher teacher, Set<Student> students) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.students = students;
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
