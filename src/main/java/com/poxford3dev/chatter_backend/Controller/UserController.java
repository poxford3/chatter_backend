package com.poxford3dev.chatter_backend.Controller;

import com.poxford3dev.chatter_backend.Dtos.SessionDto;
import com.poxford3dev.chatter_backend.Dtos.SessionRequest;
import com.poxford3dev.chatter_backend.Dtos.UserDto;
import com.poxford3dev.chatter_backend.Entity.Session;
import com.poxford3dev.chatter_backend.Entity.User;
import com.poxford3dev.chatter_backend.Mappers.UserMapper;
import com.poxford3dev.chatter_backend.Payload.Request.EditedUserRequest;
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

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> editUser(@RequestBody EditedUserRequest editedUserRequest, @PathVariable Integer id) {
        User editedUser = userService.editUser(id, editedUserRequest);
        return ResponseEntity.ok(userMapper.toDto(editedUser));
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
