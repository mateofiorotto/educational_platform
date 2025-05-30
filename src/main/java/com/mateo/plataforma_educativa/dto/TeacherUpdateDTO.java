package com.mateo.plataforma_educativa.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mateo.plataforma_educativa.model.Course;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

public class TeacherUpdateDTO {
    @NotBlank(message = "Name is Required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    public TeacherUpdateDTO(){}

    public TeacherUpdateDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
