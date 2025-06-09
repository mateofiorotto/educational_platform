package com.mateo.plataforma_educativa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class PermissionRequestDTO {

    @NotBlank(message = "Permission is Required")
    @Size(min = 3, max = 20, message = "Permission must be between 3 and 20 characters")
    private String permission;

    public PermissionRequestDTO() {}

    public PermissionRequestDTO(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
