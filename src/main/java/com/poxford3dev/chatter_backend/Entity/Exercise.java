package com.poxford3dev.chatter_backend.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "exercises")
@NoArgsConstructor
@AllArgsConstructor
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Integer reps;
    private Integer sets;
    private BigDecimal weight;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;
}
