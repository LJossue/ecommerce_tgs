
package com.challenge.ecommerce.repository;

import com.challenge.ecommerce.model.ERole;
import com.challenge.ecommerce.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IRoleRepository extends JpaRepository<Role, Long>{
    Role findByName(ERole name);
}
