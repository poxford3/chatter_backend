package com.poxford3dev.chatter_backend.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Data
@Table(name = "sessions")
@NoArgsConstructor
@AllArgsConstructor
public class Session {

    @Id
    private String id;

    private String name;
    private Integer duration; // milliseconds
    private Date date;
    private boolean active;
    private Object type;

}
