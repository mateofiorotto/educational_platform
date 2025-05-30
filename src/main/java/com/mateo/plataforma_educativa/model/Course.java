package com.mateo.plataforma_educativa.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Table(name = "courses")
@Entity
@SQLDelete(sql = "UPDATE courses SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name="teacher_id", nullable = false)
    @JsonIgnoreProperties("courses")
    private Teacher teacher;
    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();
    private boolean deleted = Boolean.FALSE;

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

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
