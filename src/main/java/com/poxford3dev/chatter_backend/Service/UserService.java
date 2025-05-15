package com.poxford3dev.chatter_backend.Service;

import com.poxford3dev.chatter_backend.Entity.User;
import com.poxford3dev.chatter_backend.Repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
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

    public void createUser(User newUser) {
        if (newUser.getName() == null || newUser.getName().isBlank()) {
            throw new IllegalArgumentException("Name is required.");
        }
        userRepo.save(newUser); // Let Spring throw if DB fails
    }

    /*
        public User updateUser(Long id, User updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setEmail(updatedUser.getEmail());
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }
         // Update other fields as needed
        return userRepository.save(existingUser);
    }
     */

    // TODO finish implementing
    public User updateUser(Integer id, User updatedUser) {
        User existingUser = userRepo.findById(id)
                .orElseThrow(() -> new InvalidConfigurationPropertyValueException("id", id, "unable to find user"));
        existingUser.setName(updatedUser.getName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setProfilePic(updatedUser.getProfilePic());

        return updatedUser;
    }

    public boolean deleteUser(Integer id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
