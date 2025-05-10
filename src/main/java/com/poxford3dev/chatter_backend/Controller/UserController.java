package com.poxford3dev.chatter_backend.Controller;

import com.poxford3dev.chatter_backend.Dtos.UserDto;
import com.poxford3dev.chatter_backend.Entity.User;
import com.poxford3dev.chatter_backend.Mappers.UserMapper;
import com.poxford3dev.chatter_backend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDto> getUsers() {
        return userService.getAllUsers()
                .stream()
//                .map(userMapper::toDto)
                .map(UserMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Integer id) {
        var user = userService.findUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(UserMapper.toDto(user));
    }

    @PostMapping
    public UserDto createUser(@RequestBody User newUser) {
        userService.createUser(newUser);
        return UserMapper.toDto(newUser);
    }

    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable("id") Integer id) {
        return userService.deleteUser(id);
    }
}
