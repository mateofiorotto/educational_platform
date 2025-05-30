package com.mateo.plataforma_educativa.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mateo.plataforma_educativa.model.Course;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.HashSet;
import java.util.Set;

public class TeacherIdDTO {
    @NotNull
    @Positive
    private Long id;

    public TeacherIdDTO(){}

    public TeacherIdDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
