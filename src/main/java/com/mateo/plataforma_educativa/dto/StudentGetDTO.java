package com.mateo.plataforma_educativa.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mateo.plataforma_educativa.model.Course;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class StudentGetDTO implements Serializable {
    private String name;
    private Set<CourseGetDTO> courses = new HashSet<>();

    public StudentGetDTO(){}

    public StudentGetDTO(String name, Set<CourseGetDTO> courses) {
        this.name = name;
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CourseGetDTO> getCourses() {
        return courses;
    }

    public void setCourses(Set<CourseGetDTO> courses) {
        this.courses = courses;
    }
}
