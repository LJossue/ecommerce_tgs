/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.challenge.ecommerce.service;

import com.challenge.ecommerce.model.ERole;
import com.challenge.ecommerce.model.Role;
import com.challenge.ecommerce.repository.IRoleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author leand
 */
@Service
public class RoleService implements IRoleService {

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public List<Role> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles;
    }

    @Override
    public Role findRoleById(Long id) {
        Role role = roleRepository.findById(id).orElse(null);
        return role;
    }
    
    @Override
    public Role findRoleByName(ERole name) {
        return roleRepository.findByName(name);
    }

    @Override
    public void updateRole(Long id, ERole name) {
       Role role = findRoleById(id);
        if (role != null) {
            role.setName(name);
            saveRole(role);
        }
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

}
