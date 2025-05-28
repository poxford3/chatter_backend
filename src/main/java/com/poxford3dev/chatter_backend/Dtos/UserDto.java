package com.poxford3dev.chatter_backend.Dtos;

import com.poxford3dev.chatter_backend.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.Set;

@AllArgsConstructor
@Getter
public class  UserDto {
    private Integer id;
    private String name;
    private String username;
    private String profilePic;
    private String email;
    private Timestamp created;
    private Set<Role> roles;
}
