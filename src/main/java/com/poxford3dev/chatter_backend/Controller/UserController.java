package com.poxford3dev.chatter_backend.Controller;

import com.poxford3dev.chatter_backend.Dtos.UserDto;
import com.poxford3dev.chatter_backend.Entity.User;
import com.poxford3dev.chatter_backend.Mappers.UserMapper;
import com.poxford3dev.chatter_backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping
    public List<UserDto> getUsers() {
        return userService.getAllUsers()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Integer id) {
        var user = userService.findUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User newUser) {
        try {
            userService.createUser(newUser);
            UserDto userDto = userMapper.toDto(newUser);
            return ResponseEntity.status(201).body(userDto); // 201 Created
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(409).body("User already exists or violates constraints.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body("Invalid user data.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Unexpected error occurred.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Integer id) {
        if (userService.deleteUser(id))  {
            return ResponseEntity.ok("User deleted");
        } else {
            return ResponseEntity.status(400).body("Not Found");
        }
    }
}
