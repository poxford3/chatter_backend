package com.poxford3dev.chatter_backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String email;
    private String password;
    private Timestamp created;

    @Column(name = "profile_pic")
    private String profilePic;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<SessionUser> sessionUsers;
}
