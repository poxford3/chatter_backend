package com.poxford3dev.chatter_backend.Repository;

import com.poxford3dev.chatter_backend.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

// userful for init search types
// https://www.geeksforgeeks.org/findby-methods-in-spring-data-jpa-repositories/
public interface UserRepo extends JpaRepository<User, Integer> {
}

