package com.poxford3dev.chatter_backend.Repository;

import com.poxford3dev.chatter_backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// userful for init search types
// https://www.geeksforgeeks.org/findby-methods-in-spring-data-jpa-repositories/
public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}

