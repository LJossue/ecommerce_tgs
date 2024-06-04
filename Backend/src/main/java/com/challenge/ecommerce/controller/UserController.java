/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.challenge.ecommerce.controller;

import com.challenge.ecommerce.dto.CreateUserDTO;
import com.challenge.ecommerce.model.ERole;
import com.challenge.ecommerce.model.Role;
import com.challenge.ecommerce.model.UserEntity;
import com.challenge.ecommerce.service.IRoleService;
import com.challenge.ecommerce.service.IUserService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author leand
 */
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")

public class UserController {

    @Autowired
    private IUserService userService;

   @Autowired
    private IRoleService roleService;

    @PostMapping
    public ResponseEntity<UserEntity> createUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
        Set<Role> roles = createUserDTO.getRoles().stream()
                .map(role -> roleService.findRoleByName(ERole.valueOf(role))) // Asumimos que hay un método en RoleService para encontrar roles por nombre
                .collect(Collectors.toSet());

        UserEntity userEnitity = UserEntity.builder()
                .username(createUserDTO.getUsername())
                .password(createUserDTO.getPassword())
                .email(createUserDTO.getEmail())
                .roles(roles)
                .build();

        userService.saveUser(userEnitity);
        return ResponseEntity.ok(userEnitity);
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> getAllUsers() {
        List<UserEntity> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long id) {
        UserEntity user = userService.findUserById(id);
        return ResponseEntity.ok(user);
    }

   @PutMapping("/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @Valid @RequestBody CreateUserDTO userDto) {
        Set<Role> roles = userDto.getRoles().stream()
                .map(roleName -> roleService.findRoleByName(ERole.valueOf(roleName)))
                .collect(Collectors.toSet());

        userService.updateUser(id, userDto.getUsername(), userDto.getPassword(), userDto.getEmail(), roles);
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/{id}") 
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/username/{username}")
    public ResponseEntity<UserEntity> getUserByUsername(@PathVariable String username) {
        UserEntity user = userService.findUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}/roles")
    public ResponseEntity<UserEntity> updateUserRoles(@PathVariable Long id, @RequestBody Set<Role> roles) {
        userService.updateUserRoles(id, roles);
        UserEntity user = userService.findUserById(id);
        return ResponseEntity.ok(user);
    }
}
