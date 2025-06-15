package com.mateo.plataforma_educativa.controller;

import com.mateo.plataforma_educativa.dto.PermissionRequestDTO;
import com.mateo.plataforma_educativa.dto.PermissionResponseDTO;
import com.mateo.plataforma_educativa.dto.ResponseDTO;
import com.mateo.plataforma_educativa.model.Permission;
import com.mateo.plataforma_educativa.service.IPermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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

    @Operation(summary = "Get permissions",
            description = "Return the list of permissions. Only users with ADMIN role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Permissions returned succesfully"),
            @ApiResponse(responseCode = "403", description = "Access Denied"),
            @ApiResponse(responseCode = "500", description = "Invalid Token. (Unauthorized/Not Authenticated)"),
    })
    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getPermissions() {
        List<PermissionResponseDTO> permissions = permissionService.getPermissions();

        ResponseDTO<List<PermissionResponseDTO>> getResponsePermissions = new ResponseDTO<>(permissions, 200, "Permissions returned successfully");

        return ResponseEntity.ok(getResponsePermissions);
    }

    @Operation(summary = "Get permission",
            description = "Return one permission. Only users with ADMIN role.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Permission returned succesfully"),
            @ApiResponse(responseCode = "403", description = "Access Denied"),
            @ApiResponse(responseCode = "404", description = "Permission not found"),
            @ApiResponse(responseCode = "500", description = "Invalid Token. (Unauthorized/Not Authenticated)")
    })
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getPermissionById(@PathVariable Long id){

        PermissionResponseDTO permission = permissionService.getPermissionById(id);

        ResponseDTO<PermissionResponseDTO> getResponsePermission = new ResponseDTO<>(permission, 200, "Permission returned succesfully");

        return ResponseEntity.ok(getResponsePermission);
    }

    @Operation(summary = "Create a permission",
            description = "Return the created permission. Only the users with ADMIN role can create new permissions.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Permission created succesfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request, fields validation error"),
            @ApiResponse(responseCode = "403", description = "Access Denied"),
            @ApiResponse(responseCode = "500", description = "Invalid Token. (Unauthorized/Not Authenticated)")
    })
    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> savePermission(@Valid @RequestBody PermissionRequestDTO permission){
        permissionService.savePermission(permission);

        ResponseDTO<PermissionRequestDTO> savePermissionResponse = new ResponseDTO<>(permission, 200, "Permission saved succesfully");

        return ResponseEntity.status(HttpStatus.CREATED).body(savePermissionResponse);
    }

    @Operation(summary = "Edit a permission",
            description = "Return the edited permission. Only the users with ADMIN role can edit permissions.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Permission edited succesfully"),
            @ApiResponse(responseCode = "400", description = "Bad Request, fields validation error"),
            @ApiResponse(responseCode = "403", description = "Access Denied"),
            @ApiResponse(responseCode = "404", description = "Permission not found"),
            @ApiResponse(responseCode = "500", description = "Invalid Token. (Unauthorized/Not Authenticated)")
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updatePermission(@Valid @RequestBody PermissionRequestDTO permission, @PathVariable Long id){
        permissionService.updatePermission(permission, id);

        ResponseDTO<PermissionRequestDTO> updatePermissionResponse = new ResponseDTO<>(permission, 200, "Permission updated succesfully");

        return ResponseEntity.status(HttpStatus.OK).body(updatePermissionResponse);
    }

    @Operation(summary = "Delete a permission",
            description = "Return a confirmation message. Only the users with ADMIN role can delete permissions.",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Permission deleted succesfully"),
            @ApiResponse(responseCode = "403", description = "Access Denied"),
            @ApiResponse(responseCode = "404", description = "Permission not found"),
            @ApiResponse(responseCode = "500", description = "Invalid Token. (Unauthorized/Not Authenticated)")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deletePermission(@PathVariable Long id){
        permissionService.deletePermission(id);

        return ResponseEntity.status(HttpStatus.OK).body("Permission deleted");
    }
}
