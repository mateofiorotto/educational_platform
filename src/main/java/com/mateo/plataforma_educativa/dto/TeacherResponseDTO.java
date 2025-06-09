package com.mateo.plataforma_educativa.dto;

public class TeacherResponseDTO {
    private String name;

    public TeacherResponseDTO(){}

    public TeacherResponseDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
