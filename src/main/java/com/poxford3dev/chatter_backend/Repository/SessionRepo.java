package com.poxford3dev.chatter_backend.Repository;

import com.poxford3dev.chatter_backend.Entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepo extends JpaRepository<Session, String> {
}
