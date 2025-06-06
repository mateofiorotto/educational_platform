package com.mateo.plataforma_educativa.service;

import com.mateo.plataforma_educativa.model.UserSec;

import java.util.List;

public interface IUserService {
    /**
     * Returns a list of all users
     *
     * @return list of users
     * */
    public List<UserSec> getUsers();

    /**
     * Returns a specific user
     *
     * @param id
     * @return a user
     * */
    public UserSec getUserById(Long id);

    /**
     * Create a user
     *
     * @param user
     * @return the created user info
     * */
    public UserSec saveUser(UserSec user);

    /**
     * Update a user by id
     *
     * @param user
     * @param id
     * @return the updated user
     * */
    public UserSec updateUser(UserSec user, Long id);

    /**
     * Delete a user
     * @param id
     * */
    public void deleteUser(Long id);

    /**
     * Encript the user password
     *
     * @param password
     * @return encripted password
     * */
    public String encriptPassword(String password);
}
