package com.poxford3dev.chatter_backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "friends")
@IdClass(FriendId.class)
@NoArgsConstructor
@AllArgsConstructor
public class Friend {

    @Id
    @Column(name = "friend_one")
    private Integer friendOne;

    @Id
    @Column(name = "friend_two")
    private Integer friendTwo;
}
