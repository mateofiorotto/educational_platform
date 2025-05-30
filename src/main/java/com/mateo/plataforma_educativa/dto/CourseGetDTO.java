package com.mateo.plataforma_educativa.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.mateo.plataforma_educativa.model.Student;
import com.mateo.plataforma_educativa.model.Teacher;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CourseGetDTO implements Serializable {
    private String name;
    private TeacherGetDTO teacher; //replace for DTO TeacherGetDTO

    public CourseGetDTO(){}

    public CourseGetDTO(String name, TeacherGetDTO teacher) {
        this.name = name;
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TeacherGetDTO getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherGetDTO teacher) {
        this.teacher = teacher;
    }
}
