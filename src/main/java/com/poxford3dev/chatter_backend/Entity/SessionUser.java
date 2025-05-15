package com.poxford3dev.chatter_backend.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "session_users")
@NoArgsConstructor
@AllArgsConstructor
public class SessionUser {
    @Id
    @Column(name = "session_id")
    private String sessionId;

    @Id
    @Column(name = "user_id")
    private Integer userId;
}
