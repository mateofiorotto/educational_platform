package com.mateo.plataforma_educativa.model;

import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Table(name = "students")
@Entity
@SQLDelete(sql = "UPDATE students SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    //el estudiante maneja la relacion porque ellos se inscriben a materias
    //si fuera el curso quien los inscribe, seria al reves
    @JoinTable(
            name = "students_courses",
            joinColumns = { @JoinColumn(name = "student_id") },
            inverseJoinColumns = { @JoinColumn(name = "course_id") }
    )
    private Set<Course> courses = new HashSet<>();
    private boolean deleted = Boolean.FALSE;

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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(id, student.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
