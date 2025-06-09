package com.mateo.plataforma_educativa.dto;

public class PermissionResponseDTO {
    private String permission;

    public PermissionResponseDTO() {}

    public PermissionResponseDTO(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
