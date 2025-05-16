package com.poxford3dev.chatter_backend.Dtos;

import com.poxford3dev.chatter_backend.Entity.Exercise;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SessionRequest {
    public String id;
    public String name;
    private Integer duration;
    private Timestamp created;
    private boolean active;
    private String type;
    private List<Integer> userIds;
    private List<Exercise> exercises;
}
