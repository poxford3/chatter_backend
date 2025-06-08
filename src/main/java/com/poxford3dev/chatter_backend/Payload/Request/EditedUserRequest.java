package com.poxford3dev.chatter_backend.Payload.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditedUserRequest {
    private String username;
    private String name;
    private String email;
    private String password;
    private Set<String> role; // Role names like "ROLE_USER", "ROLE_ADMIN"
}
