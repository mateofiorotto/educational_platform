package com.mateo.plataforma_educativa.service;

import com.mateo.plataforma_educativa.dto.RoleRequestDTO;
import com.mateo.plataforma_educativa.dto.RoleResponseDTO;
import com.mateo.plataforma_educativa.model.Role;

import java.util.List;

public interface IRoleService {
    /**
     * Returns a list of all roles
     *
     * @return list of roles
     * */
    public List<RoleResponseDTO> getRoles();

    /**
     * Returns a specific role
     *
     * @param id
     * @return a role
     * */
    public RoleResponseDTO getRoleById(Long id);

    /**
     * Create a role
     *
     * @param role
     * @return the created role info
     * */
    public RoleRequestDTO saveRole(RoleRequestDTO role);

    /**
     * Update a role by id
     *
     * @param role
     * @param id
     * @return the updated role
     * */
    public RoleRequestDTO updateRole(RoleRequestDTO role, Long id);

    /**
     * Delete a role
     * @param id
     * */
    public void deleteRole(Long id);
}
