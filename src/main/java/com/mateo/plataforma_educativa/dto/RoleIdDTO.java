package com.mateo.plataforma_educativa.dto;

import com.mateo.plataforma_educativa.model.Permission;
import jakarta.persistence.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class RoleIdDTO {
    private Long id;

    public RoleIdDTO() {}

    public RoleIdDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
