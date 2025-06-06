package com.mateo.plataforma_educativa.service;

import com.mateo.plataforma_educativa.exception.NotFoundException;
import com.mateo.plataforma_educativa.model.Role;
import com.mateo.plataforma_educativa.model.UserSec;
import com.mateo.plataforma_educativa.repository.IUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final RoleService roleService;

    public UserService(IUserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public List<UserSec> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserSec getUserById(Long id) {
        UserSec user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found with ID: " + id));

        return user;
    }

    @Override
    public UserSec saveUser(UserSec user) {

        Set<Role> roleList = new HashSet<>();
        Role readRole;

        //Encript password
        user.setPassword(this.encriptPassword(user.getPassword()));

        for(Role r : user.getRoles()){
            readRole = roleService.getRoleById(r.getId());

            if(readRole != null){
                roleList.add(readRole);
            }
        }

        user.setRoles(roleList);

        return userRepository.save(user);
    }

    @Override
    public UserSec updateUser(UserSec user, Long id) {
        UserSec userToUpdate = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found with ID: " + id));

        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setPassword(this.encriptPassword(user.getPassword()));
        userToUpdate.setEnabled(user.isEnabled());
        userToUpdate.setAccountNotExpired(user.isAccountNotExpired());
        userToUpdate.setAccountNotLocked(user.isAccountNotLocked());
        userToUpdate.setCredentialNotExpired(user.isCredentialNotExpired());

        Set<Role> roleList = new HashSet<>();
        for(Role r : user.getRoles()){
            Role readRole = roleService.getRoleById(r.getId());
            System.out.println(r.getId());

            if(readRole != null){
                roleList.add(readRole);
            }
            System.out.println(readRole);
        }

        userToUpdate.setRoles(roleList);

        return userRepository.save(userToUpdate);
    }

    @Override
    public void deleteUser(Long id) {
        UserSec userToDelete = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found with ID: " + id));

        userToDelete.getRoles().clear();

        userRepository.delete(userToDelete);
    }

    @Override
    public String encriptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
