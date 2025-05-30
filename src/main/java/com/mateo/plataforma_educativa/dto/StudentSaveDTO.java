package com.mateo.plataforma_educativa.dto;

import com.mateo.plataforma_educativa.model.Course;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class StudentSaveDTO implements Serializable {
    @NotBlank(message = "Name is Required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;
    @NotNull(message = "Courses are required")
    private Set<CourseIdDTO> courses = new HashSet<>();

    public StudentSaveDTO(){}

    public StudentSaveDTO(String name, Set<CourseIdDTO> courses) {
        this.name = name;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CourseIdDTO> getCourses() {
        return courses;
    }

    public void setCourses(Set<CourseIdDTO> courses) {
        this.courses = courses;
    }
}
