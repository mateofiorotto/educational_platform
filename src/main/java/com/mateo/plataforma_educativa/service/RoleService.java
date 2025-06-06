package com.mateo.plataforma_educativa.service;

import com.mateo.plataforma_educativa.dto.ResponseDTO;
import com.mateo.plataforma_educativa.exception.NotFoundException;
import com.mateo.plataforma_educativa.model.Course;
import com.mateo.plataforma_educativa.model.Permission;
import com.mateo.plataforma_educativa.model.Role;
import com.mateo.plataforma_educativa.repository.IRoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoleService implements IRoleService {

    private final IRoleRepository roleRepository;
    private final PermissionService permissionService;

    public RoleService(IRoleRepository roleRepository, PermissionService permissionService) {
        this.roleRepository = roleRepository;
        this.permissionService = permissionService;
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new NotFoundException("Role not found with ID: " + id));

        return role;
    }

    @Override
    public Role saveRole(Role role) {

        Set<Permission> permissionList = new HashSet<>();
        Permission readPermission;

        for (Permission per : role.getPermissions()) {
            readPermission = permissionService.getPermissionById(per.getId());

            if (readPermission != null) {
                permissionList.add(readPermission);
            }
        }

        role.setPermissions(permissionList);

        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Role role, Long id) {
        Role roleToUpdate = roleRepository.findById(id).orElseThrow(() -> new NotFoundException("Role not found with ID: " + id));

        roleToUpdate.setRole(role.getRole());

        Set<Permission> permissionList = new HashSet<>();
        for (Permission per : role.getPermissions()) {

            Permission readPermission = permissionService.getPermissionById(per.getId());
            if (readPermission != null) {
                permissionList.add(readPermission);
            }
        }

        roleToUpdate.setPermissions(permissionList);

        return roleRepository.save(roleToUpdate);
    }

    @Override
    public void deleteRole(Long id) {
        Role roleToDelete = roleRepository.findById(id).orElseThrow(() -> new NotFoundException("Role not found with ID: " + id));

        roleToDelete.getPermissions().clear();

        roleRepository.delete(roleToDelete);
    }
}
