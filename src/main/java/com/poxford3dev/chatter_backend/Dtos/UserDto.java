package com.poxford3dev.chatter_backend.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@AllArgsConstructor
@Getter
public class  UserDto {
    private Integer id;
    private String name;
    private String profilePic;
    private Timestamp created;
}
