package com.poxford3dev.chatter_backend.Service;

import com.poxford3dev.chatter_backend.Entity.Session;
import com.poxford3dev.chatter_backend.Repository.SessionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
// TODO
@Service
public class SessionService {

    @Autowired
    private SessionRepo sessionRepo;

    public List<Session> getAllSessions() {
        return sessionRepo.findAll();
    }

    public Session createSession(Session newSesh) {
        return sessionRepo.save(newSesh);
    }

//    public boolean deleteSession(String id) {
//        if ()
//    }
}
