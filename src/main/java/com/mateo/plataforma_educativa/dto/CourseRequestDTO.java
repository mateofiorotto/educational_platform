package com.mateo.plataforma_educativa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

public class CourseRequestDTO implements Serializable {
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;
    @NotNull(message = "Teacher is required")
    private TeacherIdDTO teacher;

    public CourseRequestDTO(){}

    public CourseRequestDTO(String name, TeacherIdDTO teacher) {
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
