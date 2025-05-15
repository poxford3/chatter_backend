package com.poxford3dev.chatter_backend.Dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Date;
import java.sql.Timestamp;

@AllArgsConstructor
@Getter
public class SessionDto {
    private String id;
    private String name;
    private Integer duration;
    private Timestamp created;
    private boolean active;
    private String type;
}
