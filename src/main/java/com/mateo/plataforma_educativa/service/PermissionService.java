package com.mateo.plataforma_educativa.service;

import com.mateo.plataforma_educativa.dto.TeacherGetDTO;
import com.mateo.plataforma_educativa.dto.TeacherSaveDTO;
import com.mateo.plataforma_educativa.dto.TeacherUpdateDTO;
import com.mateo.plataforma_educativa.exception.NotFoundException;
import com.mateo.plataforma_educativa.mapper.ITeacherMapper;
import com.mateo.plataforma_educativa.model.Permission;
import com.mateo.plataforma_educativa.model.Teacher;
import com.mateo.plataforma_educativa.repository.IPermissionRepository;
import com.mateo.plataforma_educativa.repository.ITeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissionService implements IPermissionService {

    private final IPermissionRepository permissionRepository;

    public PermissionService(IPermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public List<Permission> getPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public Permission getPermissionById(Long id) {
        Permission permission = permissionRepository.findById(id).orElseThrow(() -> new NotFoundException("Permission not found with ID: " + id));

        return permission;
    }

    @Override
    public Permission savePermission(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public Permission updatePermission(Permission permission, Long id) {
        Permission permissionToUpdate = permissionRepository.findById(id).orElseThrow(() -> new NotFoundException("Permission not found with ID: " + id));

        permissionToUpdate.setPermission(permission.getPermission());

        return permissionRepository.save(permissionToUpdate);
    }

    @Override
    public void deletePermission(Long id) {
        Permission permissionToDelete = permissionRepository.findById(id).orElseThrow(() -> new NotFoundException("Permission not found with ID: " + id));

        permissionRepository.delete(permissionToDelete);
    }
}
