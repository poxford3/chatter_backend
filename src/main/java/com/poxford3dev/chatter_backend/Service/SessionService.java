package com.poxford3dev.chatter_backend.Service;

import com.poxford3dev.chatter_backend.Entity.Session;
import com.poxford3dev.chatter_backend.Mappers.SessionMapper;
import com.poxford3dev.chatter_backend.Repository.SessionRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {

    @Autowired
    private SessionRepo sessionRepo;

    public List<Session> getAllSessions() {
        return sessionRepo.findAll();
    }

    public Session getSessionById(String id) {
        return sessionRepo.findById(id).orElse(null);
    }

    public void createSession(Session newSesh) {
        sessionRepo.save(newSesh);
    }

    public void editSession(Session editedSession, String id) {
        if (sessionRepo.existsById(id)) {
            // save and flush makes sure the changes go through
            sessionRepo.saveAndFlush(editedSession);
        } else {
            throw new EntityNotFoundException("Session with id (" + id + ") not found");
        }
    }

    public boolean deleteSession(String id) {
        if (sessionRepo.existsById(id)) {
            sessionRepo.deleteById(id);
            return true;
        } else {
            throw new EntityNotFoundException("Session with id (" + id + ") not found");
        }
    }
}
