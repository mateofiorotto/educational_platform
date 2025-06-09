package com.mateo.plataforma_educativa.dto;

import java.io.Serializable;

public class CourseResponseDTO implements Serializable {
    private String name;
    private TeacherResponseDTO teacher; //replace for DTO TeacherGetDTO

    public CourseResponseDTO(){}

    public CourseResponseDTO(String name, TeacherResponseDTO teacher) {
        this.name = name;
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TeacherResponseDTO getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherResponseDTO teacher) {
        this.teacher = teacher;
    }
}
