package com.mateo.plataforma_educativa.dto;

import com.mateo.plataforma_educativa.model.Student;
import com.mateo.plataforma_educativa.model.Teacher;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CourseUpdateDTO implements Serializable {
    private Long id;
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;
    @NotNull(message = "Teacher is required")
    @Positive(message = "Teacher ID must be positive")
    private Teacher teacher; //replace for DTO TeacherGetDTO
    @NotNull(message = "Students are required")
    @Positive(message = "Student ID must be positive")
    private Set<Student> students = new HashSet<>(); //replace for DTO StudentGetDTO

    public CourseUpdateDTO(){}

    public CourseUpdateDTO(Long id, String name, Teacher teacher, Set<Student> students) {
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
