package com.mateo.plataforma_educativa.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class StudentResponseDTO implements Serializable {
    private String name;
    private Set<CourseResponseDTO> courses = new HashSet<>();

    public StudentResponseDTO(){}

    public StudentResponseDTO(String name, Set<CourseResponseDTO> courses) {
        this.name = name;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CourseResponseDTO> getCourses() {
        return courses;
    }

    public void setCourses(Set<CourseResponseDTO> courses) {
        this.courses = courses;
    }
}
