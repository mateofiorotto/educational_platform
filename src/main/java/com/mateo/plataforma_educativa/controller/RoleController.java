package com.mateo.plataforma_educativa.controller;

import com.mateo.plataforma_educativa.dto.ResponseDTO;
import com.mateo.plataforma_educativa.dto.RoleRequestDTO;
import com.mateo.plataforma_educativa.dto.RoleResponseDTO;
import com.mateo.plataforma_educativa.model.Permission;
import com.mateo.plataforma_educativa.model.Role;
import com.mateo.plataforma_educativa.service.IPermissionService;
import com.mateo.plataforma_educativa.service.IRoleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/roles")
@PreAuthorize("denyAll()")
public class RoleController {
    private final IRoleService roleService;
    private final IPermissionService permissionService;

    public RoleController(IRoleService roleService, IPermissionService permissionService) {
        this.roleService = roleService;
        this.permissionService = permissionService;
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getRoles() {
        List<RoleResponseDTO> roles = roleService.getRoles();

        ResponseDTO<List<RoleResponseDTO>> getResponseRoles = new ResponseDTO<>(roles, 200, "Roles returned successfully");

        return ResponseEntity.ok(getResponseRoles);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getRoleById(@PathVariable Long id){

        RoleResponseDTO role = roleService.getRoleById(id);

        ResponseDTO<RoleResponseDTO> getResponseRole = new ResponseDTO<>(role, 200, "Role returned succesfully");

        return ResponseEntity.ok(getResponseRole);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> saveRole(@Valid @RequestBody RoleRequestDTO role){
        roleService.saveRole(role);

        ResponseDTO<RoleRequestDTO> saveRoleResponse = new ResponseDTO<>(role, 200, "Role saved succesfully");

        return ResponseEntity.status(HttpStatus.CREATED).body(saveRoleResponse);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateRole(@Valid @RequestBody RoleRequestDTO role, @PathVariable Long id){
        roleService.updateRole(role, id);

        ResponseDTO<RoleRequestDTO> updateRoleResponse = new ResponseDTO<>(role, 200, "Role updated succesfully");

        return ResponseEntity.status(HttpStatus.OK).body(updateRoleResponse);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteRole(@PathVariable Long id){
        roleService.deleteRole(id);

        return ResponseEntity.status(HttpStatus.OK).body("Role deleted");
    }
}
