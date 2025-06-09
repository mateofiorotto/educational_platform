package com.mateo.plataforma_educativa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

public class UserSecRequestDTO {
    @NotBlank(message = "Username is Required")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;
    @NotBlank(message = "Password is Required")
    private String password;
    private boolean enabled = true;
    private boolean accountNotExpired = true;
    private boolean accountNotLocked = true;
    private boolean credentialNotExpired = true;
    @NotEmpty(message = "At least one role must be assigned")
    private Set<RoleIdDTO> roles = new HashSet<>();

    public UserSecRequestDTO(){}

    public UserSecRequestDTO(String username, String password, boolean enabled, boolean accountNotExpired, boolean accountNotLocked, boolean credentialNotExpired, Set<RoleIdDTO> roles) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.accountNotExpired = accountNotExpired;
        this.accountNotLocked = accountNotLocked;
        this.credentialNotExpired = credentialNotExpired;
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAccountNotExpired() {
        return accountNotExpired;
    }

    public void setAccountNotExpired(boolean accountNotExpired) {
        this.accountNotExpired = accountNotExpired;
    }

    public boolean isAccountNotLocked() {
        return accountNotLocked;
    }

    public void setAccountNotLocked(boolean accountNotLocked) {
        this.accountNotLocked = accountNotLocked;
    }

    public boolean isCredentialNotExpired() {
        return credentialNotExpired;
    }

    public void setCredentialNotExpired(boolean credentialNotExpired) {
        this.credentialNotExpired = credentialNotExpired;
    }

    public Set<RoleIdDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleIdDTO> roles) {
        this.roles = roles;
    }
}
