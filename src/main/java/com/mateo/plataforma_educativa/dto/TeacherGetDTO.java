package com.mateo.plataforma_educativa.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mateo.plataforma_educativa.model.Course;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

public class TeacherGetDTO {
    private String name;

    public TeacherGetDTO(){}

    public TeacherGetDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
