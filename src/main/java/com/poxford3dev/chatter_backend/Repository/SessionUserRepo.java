package com.poxford3dev.chatter_backend.Repository;

import com.poxford3dev.chatter_backend.Entity.Session;
import com.poxford3dev.chatter_backend.Entity.SessionUser;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionUserRepo extends JpaRepository<SessionUser, Integer> {
    @Transactional
    void deleteAllBySession(Session originalSession);
}
