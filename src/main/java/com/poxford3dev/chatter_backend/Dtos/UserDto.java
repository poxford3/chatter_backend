package com.poxford3dev.chatter_backend.Dtos;

import com.poxford3dev.chatter_backend.Entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private Integer id;
    private String name;
    private String username;
    private String profilePicBase64;
    private String email;
    private Timestamp created;
    private Set<Role> roles;
}
