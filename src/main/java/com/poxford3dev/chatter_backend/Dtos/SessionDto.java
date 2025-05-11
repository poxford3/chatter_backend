package com.poxford3dev.chatter_backend.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@Getter
public class SessionDto {
    private String id;
    private String name;
    private Integer duration;
    private Date date;
    private boolean active;
    private String type;
}
