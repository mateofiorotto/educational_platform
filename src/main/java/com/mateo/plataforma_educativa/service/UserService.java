package com.mateo.plataforma_educativa.service;

import com.mateo.plataforma_educativa.dto.RoleIdDTO;
import com.mateo.plataforma_educativa.dto.UserSecRequestDTO;
import com.mateo.plataforma_educativa.dto.UserSecResponseDTO;
import com.mateo.plataforma_educativa.dto.UserSecUpdateDTO;
import com.mateo.plataforma_educativa.exception.BadRequestException;
import com.mateo.plataforma_educativa.exception.NotFoundException;
import com.mateo.plataforma_educativa.mapper.IUserMapper;
import com.mateo.plataforma_educativa.model.Role;
import com.mateo.plataforma_educativa.model.UserSec;
import com.mateo.plataforma_educativa.repository.IRoleRepository;
import com.mateo.plataforma_educativa.repository.IUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;

    public UserService(IUserRepository userRepository, IRoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<UserSecResponseDTO> getUsers() {
        List<UserSec> users = userRepository.findAll();

        return users.stream().map(user -> IUserMapper.mapper.userSecToUserSecGetDTO(user)).collect(Collectors.toList());
    }

    @Override
    public UserSecResponseDTO getUserById(Long id) {
        UserSec user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found with ID: " + id));

        return IUserMapper.mapper.userSecToUserSecGetDTO(user);
    }

    @Override
    public UserSecRequestDTO saveUser(UserSecRequestDTO user) {

        UserSec userToSave = IUserMapper.mapper.userSecSaveDTOToUserSec(user);

        userToSave.setPassword(this.encriptPassword(user.getPassword()));

        Set<Role> roleList = new HashSet<>();
        for (RoleIdDTO r : user.getRoles()) {
            Role existingRole = roleRepository.findById(r.getId()).orElseThrow(() -> new BadRequestException("Role not found"));

            roleList.add(existingRole);
        }

        userToSave.setRoles(roleList);
        UserSec savedUser = userRepository.save(userToSave);

        return IUserMapper.mapper.userSecToUserSecSaveDTO(savedUser);
    }

    @Override
    public UserSecUpdateDTO updateUser(UserSecUpdateDTO user, Long id) {
        UserSec userToUpdate = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + id));

        userToUpdate.setPassword(this.encriptPassword(user.getPassword()));
        userToUpdate.setEnabled(user.isEnabled());
        userToUpdate.setAccountNotExpired(user.isAccountNotExpired());
        userToUpdate.setAccountNotLocked(user.isAccountNotLocked());
        userToUpdate.setCredentialNotExpired(user.isCredentialNotExpired());

        Set<Role> roleList = new HashSet<>();
        for (RoleIdDTO r : user.getRoles()) {
            Role existingRole = roleRepository.findById(r.getId())
                    .orElseThrow(() -> new BadRequestException("Role not found"));

            roleList.add(existingRole);
        }

        userToUpdate.setRoles(roleList);

        UserSec updatedUser = userRepository.save(userToUpdate);

        return IUserMapper.mapper.userSecToUserSecUpdateDTO(updatedUser);
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
