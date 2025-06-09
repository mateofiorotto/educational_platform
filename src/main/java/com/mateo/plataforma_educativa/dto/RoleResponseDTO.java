package com.mateo.plataforma_educativa.dto;

import com.mateo.plataforma_educativa.model.Permission;

import java.util.HashSet;
import java.util.Set;

public class RoleResponseDTO {
    private String role;
    private Set<PermissionResponseDTO> permissions = new HashSet<>();

    public RoleResponseDTO() {}

    public RoleResponseDTO(String role, Set<PermissionResponseDTO> permissions) {
        this.role = role;
        this.permissions = permissions;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<PermissionResponseDTO> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<PermissionResponseDTO> permissions) {
        this.permissions = permissions;
    }
}
