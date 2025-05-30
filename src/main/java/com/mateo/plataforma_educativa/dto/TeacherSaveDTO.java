package com.mateo.plataforma_educativa.dto;

import com.mateo.plataforma_educativa.model.Course;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

public class TeacherSaveDTO {
    @NotBlank(message = "Name is Required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    public TeacherSaveDTO(){}

    public TeacherSaveDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
