package com.mateo.plataforma_educativa.service;

import com.mateo.plataforma_educativa.model.Role;

import java.util.List;

public interface IRoleService {
    /**
     * Returns a list of all roles
     *
     * @return list of roles
     * */
    public List<Role> getRoles();

    /**
     * Returns a specific role
     *
     * @param id
     * @return a role
     * */
    public Role getRoleById(Long id);

    /**
     * Create a role
     *
     * @param role
     * @return the created role info
     * */
    public Role saveRole(Role role);

    /**
     * Update a role by id
     *
     * @param role
     * @param id
     * @return the updated role
     * */
    public Role updateRole(Role role, Long id);

    /**
     * Delete a role
     * @param id
     * */
    public void deleteRole(Long id);
}
