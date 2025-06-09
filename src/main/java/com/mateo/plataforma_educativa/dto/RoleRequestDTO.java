package com.mateo.plataforma_educativa.dto;

import com.mateo.plataforma_educativa.model.Permission;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

public class RoleRequestDTO {
    @NotBlank(message = "Role is Required")
    @Size(min = 3, max = 20, message = "Role must be between 3 and 20 characters")
    private String role;
    @NotEmpty(message = "At least one role must be assigned")
    private Set<PermissionIdDTO> permissions = new HashSet<>();

    public RoleRequestDTO() {}

    public RoleRequestDTO(String role, Set<PermissionIdDTO> permissions) {
        this.role = role;
        this.permissions = permissions;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<PermissionIdDTO> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<PermissionIdDTO> permissions) {
        this.permissions = permissions;
    }
}
