package com.poxford3dev.chatter_backend.Payload.Request;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class LoginRequest {
    private String username;
    private String password;
}
