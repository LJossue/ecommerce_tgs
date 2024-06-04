/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.challenge.ecommerce.service;
import com.challenge.ecommerce.model.Role;
import com.challenge.ecommerce.model.UserEntity;
import com.challenge.ecommerce.repository.IUserRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author leand
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Override
    @Transactional
    public void saveUser(UserEntity user) {
        userRepository.save(user);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void updateUser(Long id, String username, String password, String email, Set<Role> roles) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setUsername(username);
        user.setPassword(password); // Asegúrate de manejar el hashing de la contraseña adecuadamente
        user.setEmail(email);
        user.setRoles(roles);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserEntity findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public void updateUserRoles(Long id, Set<Role> roles) {
        UserEntity user = findUserById(id);
        if (user != null) {
            user.setRoles(roles);
            saveUser(user);
        }
    }
}
