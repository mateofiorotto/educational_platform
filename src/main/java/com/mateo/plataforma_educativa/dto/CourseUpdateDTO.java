package com.mateo.plataforma_educativa.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mateo.plataforma_educativa.model.Student;
import com.mateo.plataforma_educativa.model.Teacher;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CourseUpdateDTO implements Serializable {

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;
    @NotNull(message = "Teacher is required")
    private TeacherIdDTO teacher;

    public CourseUpdateDTO(){}

    public CourseUpdateDTO(String name, TeacherIdDTO teacher) {

        this.name = name;
        this.teacher = teacher;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TeacherIdDTO getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherIdDTO teacher) {
        this.teacher = teacher;
    }
}
