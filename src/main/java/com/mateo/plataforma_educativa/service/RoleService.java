package com.mateo.plataforma_educativa.service;

import com.mateo.plataforma_educativa.dto.PermissionIdDTO;
import com.mateo.plataforma_educativa.dto.ResponseDTO;
import com.mateo.plataforma_educativa.dto.RoleRequestDTO;
import com.mateo.plataforma_educativa.dto.RoleResponseDTO;
import com.mateo.plataforma_educativa.exception.BadRequestException;
import com.mateo.plataforma_educativa.exception.NotFoundException;
import com.mateo.plataforma_educativa.mapper.IRoleMapper;
import com.mateo.plataforma_educativa.model.Course;
import com.mateo.plataforma_educativa.model.Permission;
import com.mateo.plataforma_educativa.model.Role;
import com.mateo.plataforma_educativa.repository.IPermissionRepository;
import com.mateo.plataforma_educativa.repository.IRoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleService implements IRoleService {

    private final IRoleRepository roleRepository;
    private final IPermissionRepository permissionRepository;

    public RoleService(IRoleRepository roleRepository, IPermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public List<RoleResponseDTO> getRoles() {
        List<Role> roles = roleRepository.findAll();

        return roles.stream().map(role -> IRoleMapper.mapper.roleToRoleGetDTO(role)).collect(Collectors.toList());
    }

    @Override
    public RoleResponseDTO getRoleById(Long id) {
        Role role = roleRepository.findById(id).orElseThrow(() -> new NotFoundException("Role not found with ID: " + id));

        return IRoleMapper.mapper.roleToRoleGetDTO(role);
    }

    @Override
    public RoleRequestDTO saveRole(RoleRequestDTO role) {
        Role roleToSave = IRoleMapper.mapper.roleSaveDTOToRole(role);

        Set<Permission> permissionList = new HashSet<>();
        for (PermissionIdDTO per : role.getPermissions()) {
            Permission existingPermission = permissionRepository.findById(per.getId())
                    .orElseThrow(() -> new BadRequestException("Permission not found"));

            permissionList.add(existingPermission);
        }

        roleToSave.setPermissions(permissionList);
        roleRepository.save(roleToSave);

        return role;
    }

    @Override
    public RoleRequestDTO updateRole(RoleRequestDTO role, Long id) {
        Role roleToUpdate = roleRepository.findById(id).orElseThrow(() -> new NotFoundException("Role not found with ID: " + id));

        roleToUpdate.setRole(role.getRole());

        Set<Permission> permissionList = new HashSet<>();
        for (PermissionIdDTO per : role.getPermissions()) {
            Permission existingPermission = permissionRepository.findById(per.getId())
                    .orElseThrow(() -> new BadRequestException("Permission not found"));

            permissionList.add(existingPermission);
        }

        roleToUpdate.getPermissions().clear();
        roleToUpdate.getPermissions().addAll(permissionList);

        roleRepository.save(roleToUpdate);

        return role;
    }

    @Override
    public void deleteRole(Long id) {
        Role roleToDelete = roleRepository.findById(id).orElseThrow(() -> new NotFoundException("Role not found with ID: " + id));

        roleToDelete.getPermissions().clear();

        roleRepository.delete(roleToDelete);
    }
}
