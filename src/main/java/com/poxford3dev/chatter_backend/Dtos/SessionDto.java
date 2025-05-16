package com.poxford3dev.chatter_backend.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@Getter
public class SessionDto {
    private String id;
    private String name;
    private Integer duration;
    private Timestamp created;
    private boolean active;
    private String type;
    List<UserDto> users;
    List<ExerciseDto> exercises;

}
