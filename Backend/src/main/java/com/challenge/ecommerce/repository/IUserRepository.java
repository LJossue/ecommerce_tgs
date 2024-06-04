/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.challenge.ecommerce.repository;

import com.challenge.ecommerce.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author leand
 */
public interface IUserRepository extends JpaRepository<UserEntity, Long>{
        UserEntity findByUsername(String username);
}
