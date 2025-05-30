package com.mateo.plataforma_educativa.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CourseIdDTO {
    @NotNull
    @Positive
    private Long id;

    public CourseIdDTO(){}

    public CourseIdDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
