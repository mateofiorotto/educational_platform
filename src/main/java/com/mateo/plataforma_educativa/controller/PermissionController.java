package com.mateo.plataforma_educativa.controller;

import com.mateo.plataforma_educativa.dto.ResponseDTO;
import com.mateo.plataforma_educativa.model.Permission;
import com.mateo.plataforma_educativa.service.IPermissionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
@PreAuthorize("denyAll()")
public class PermissionController {
    private final IPermissionService permissionService;

    public PermissionController(IPermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getPermissions() {
        List<Permission> permissions = permissionService.getPermissions();

        ResponseDTO<List<Permission>> getResponsePermissions = new ResponseDTO<>(permissions, 200, "Permissions returned successfully");

        return ResponseEntity.ok(getResponsePermissions);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getPermissionById(@PathVariable Long id){

        Permission permission = permissionService.getPermissionById(id);

        ResponseDTO<Permission> getResponsePermission = new ResponseDTO<>(permission, 200, "Permission returned succesfully");

        return ResponseEntity.ok(getResponsePermission);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> savePermission(@Valid @RequestBody Permission permission){
        permissionService.savePermission(permission);

        ResponseDTO<Permission> savePermissionResponse = new ResponseDTO<>(permission, 200, "Permission saved succesfully");

        return ResponseEntity.status(HttpStatus.CREATED).body(savePermissionResponse);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updatePermission(@Valid @RequestBody Permission permission, @PathVariable Long id){
        permissionService.updatePermission(permission, id);

        ResponseDTO<Permission> updatePermissionResponse = new ResponseDTO<>(permission, 200, "Permission updated succesfully");

        return ResponseEntity.status(HttpStatus.OK).body(updatePermissionResponse);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deletePermission(@PathVariable Long id){
        permissionService.deletePermission(id);

        return ResponseEntity.status(HttpStatus.OK).body("Permission deleted");
    }
}
