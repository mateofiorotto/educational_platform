package com.mateo.plataforma_educativa.dto;

import com.mateo.plataforma_educativa.model.Student;
import com.mateo.plataforma_educativa.model.Teacher;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CourseSaveDTO implements Serializable {
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;
    @NotNull(message = "Teacher is required")
    private Teacher teacher; //replace for DTO TeacherGetDTO
    @NotNull(message = "Students are required")
    private Set<Student> students = new HashSet<>(); //replace for DTO StudentGetDTO

    public CourseSaveDTO(){}

    public CourseSaveDTO(String name, Teacher teacher, Set<Student> students) {
        this.name = name;
        this.teacher = teacher;
        this.students = students;
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
