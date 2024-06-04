/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.challenge.ecommerce.service;

import com.challenge.ecommerce.model.ERole;
import com.challenge.ecommerce.model.Role;
import java.util.List;

/**
 *
 * @author leand
 */
public interface IRoleService {
       void saveRole(Role role);

    List<Role> getAllRoles();

    Role findRoleById(Long id);

    public Role findRoleByName(ERole name);
    
    void updateRole(Long id, ERole name);

    void deleteRole(Long id);
}
