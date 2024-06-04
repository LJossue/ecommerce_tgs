/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.challenge.ecommerce.service;

import com.challenge.ecommerce.model.Role;
import com.challenge.ecommerce.model.UserEntity;
import java.util.List;
import java.util.Set;

/**
 *
 * @author leand
 */
public interface IUserService {
      void saveUser(UserEntity user);

    List<UserEntity> getAllUsers();

    UserEntity findUserById(Long id);

    void updateUser(Long id, String username, String password, String email, Set<Role> roles);

    void deleteUser(Long id);

    UserEntity findUserByUsername(String username);

    void updateUserRoles(Long id, Set<Role> roles);
}
