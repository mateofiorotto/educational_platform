package com.mateo.plataforma_educativa.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Table(name = "students")
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(cascade = { CascadeType.ALL })
    //el estudiante maneja la relacion porque ellos se inscriben a materias
    //si fuera el curso quien los inscribe, seria al reves
    @JoinTable(
            name = "students_courses",
            joinColumns = { @JoinColumn(name = "student_id") },
            inverseJoinColumns = { @JoinColumn(name = "course_id") }
    )
    private Set<Course> courses = new HashSet<>();

    public Student(){}

    public Student(Long id, String name, Set<Course> courses) {
        this.id = id;
        this.name = name;
        this.courses = courses;
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

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
