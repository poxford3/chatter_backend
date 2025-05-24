package com.poxford3dev.chatter_backend.Repository;

import com.poxford3dev.chatter_backend.Entity.ERole;
import com.poxford3dev.chatter_backend.Entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
