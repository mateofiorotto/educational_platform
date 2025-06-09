package com.mateo.plataforma_educativa.dto;

import jakarta.persistence.*;

import java.util.Objects;

public class PermissionIdDTO {
    private Long id;

    public PermissionIdDTO() {}

    public PermissionIdDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
