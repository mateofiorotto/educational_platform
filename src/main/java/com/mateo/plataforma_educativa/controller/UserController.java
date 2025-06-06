package com.mateo.plataforma_educativa.controller;

import com.mateo.plataforma_educativa.dto.ResponseDTO;
import com.mateo.plataforma_educativa.model.UserSec;
import com.mateo.plataforma_educativa.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@PreAuthorize("denyAll()")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getUsers() {
        List<UserSec> users = userService.getUsers();

        ResponseDTO<List<UserSec>> getResponseUsers = new ResponseDTO<>(users, 200, "Users returned successfully");

        return ResponseEntity.ok(getResponseUsers);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getUserById(@PathVariable Long id){

        UserSec user = userService.getUserById(id);

        ResponseDTO<UserSec> getResponseUser = new ResponseDTO<>(user, 200, "User returned succesfully");

        return ResponseEntity.ok(getResponseUser);
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> saveUser(@Valid @RequestBody UserSec user){
        userService.saveUser(user);

        ResponseDTO<UserSec> saveUserResponse = new ResponseDTO<>(user, 200, "User saved succesfully");

        return ResponseEntity.status(HttpStatus.CREATED).body(saveUserResponse);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserSec user, @PathVariable Long id){
        userService.updateUser(user, id);

        ResponseDTO<UserSec> updateUserResponse = new ResponseDTO<>(user, 200, "User updated succesfully");

        return ResponseEntity.status(HttpStatus.OK).body(updateUserResponse);
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);

        return ResponseEntity.status(HttpStatus.OK).body("User deleted");
    }
}
