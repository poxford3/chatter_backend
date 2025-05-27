package com.poxford3dev.chatter_backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "sessions")
@NoArgsConstructor
@AllArgsConstructor
public class Session {

    @Id
    @Column(length = 36)
    private String id;

    private String name;
    private Integer duration; // milliseconds

    @Column(insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp created;

    private boolean active;
    private String type;

    @OneToMany(mappedBy = "session")
    private List<SessionUser> sessionUsers;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exercise> exercises = new ArrayList<>();

}
