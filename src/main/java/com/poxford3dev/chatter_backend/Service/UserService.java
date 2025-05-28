package com.poxford3dev.chatter_backend.Service;

import com.poxford3dev.chatter_backend.Entity.Role;
import com.poxford3dev.chatter_backend.Entity.User;
import com.poxford3dev.chatter_backend.Payload.Request.EditedUserRequest;
import com.poxford3dev.chatter_backend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private StringToERole stringToERole;

    @Autowired
    PasswordEncoder encoder;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User findUserById(Integer id) {
        return userRepo.findById(id).orElse(null);
    }

    // TODO delete this function, covered by auth controller
//    public void createUser(User newUser) {
//        if (newUser.getName() == null || newUser.getName().isBlank()) {
//            throw new IllegalArgumentException("Name is required.");
//        }
//        userRepo.save(newUser); // Let Spring throw if DB fails
//    }

    public User editUser(Integer id, EditedUserRequest editedUser) {
        User currentUser = userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("User with id (" + id + ") not found"));

        currentUser.setUsername(editedUser.getUsername());
        currentUser.setName(editedUser.getName());
        currentUser.setEmail(editedUser.getEmail());
        currentUser.setProfilePic(editedUser.getProfilePic());

        if (editedUser.getPassword() != null) {
            currentUser.setPassword(encoder.encode(editedUser.getPassword()));
        }

        Set<Role> roles = stringToERole.stringToERole(editedUser.getRole());
        currentUser.setRoles(roles);


        currentUser = userRepo.save(currentUser);

        return currentUser;
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
