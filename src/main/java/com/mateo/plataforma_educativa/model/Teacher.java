package com.mateo.plataforma_educativa.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Table(name = "teachers")
@Entity
@SQLDelete(sql = "UPDATE teachers SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "teacher")
    private Set<Course> courses = new HashSet<>(); //one teacher can be in many courses
    private boolean deleted = Boolean.FALSE;

    public Teacher(){}

    public Teacher(Long id, String name, Set<Course> courses) {
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
        Teacher teacher = (Teacher) o;
        return Objects.equals(id, teacher.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
