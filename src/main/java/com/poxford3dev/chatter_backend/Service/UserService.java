package com.poxford3dev.chatter_backend.Service;

import com.poxford3dev.chatter_backend.Entity.User;
import com.poxford3dev.chatter_backend.Repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User findUserById(Integer id) {
        return userRepo.findById(id).orElse(null);
    }

    public User createUser(User newUser) {
        return userRepo.save(newUser);
    }

    public boolean deleteUser(Integer id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return true;
        } else {
            throw new EntityNotFoundException("User with id (" + id + ") not found");
        }
    }
}
