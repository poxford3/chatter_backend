package com.poxford3dev.chatter_backend.Service;

import com.poxford3dev.chatter_backend.Dtos.SessionRequest;
import com.poxford3dev.chatter_backend.Entity.Exercise;
import com.poxford3dev.chatter_backend.Entity.Session;
import com.poxford3dev.chatter_backend.Entity.SessionUser;
import com.poxford3dev.chatter_backend.Entity.User;
import com.poxford3dev.chatter_backend.Repository.ExerciseRepo;
import com.poxford3dev.chatter_backend.Repository.SessionRepo;
import com.poxford3dev.chatter_backend.Repository.SessionUserRepo;
import com.poxford3dev.chatter_backend.Repository.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SessionService {

    @Autowired
    private SessionRepo sessionRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ExerciseRepo exerciseRepo;

    @Autowired
    private SessionUserRepo sessionUserRepo;


    public List<Session> getAllSessions() {
        return sessionRepo.findAll();
    }

    public Session getSessionById(String id) {
        return sessionRepo.findById(id).orElse(null);
    }

//    public void createSession(Session newSesh) {
//        sessionRepo.save(newSesh);
//    }

    @Transactional
    public Session createSession(SessionRequest sessionRequest) {
        Session session = new Session();
        System.out.println("request: ");
        System.out.println(sessionRequest);
        session.setId(sessionRequest.getId());
        session.setName(sessionRequest.getName());
        session.setDuration(sessionRequest.getDuration());
        session.setActive(sessionRequest.isActive());
        session.setType(sessionRequest.getType());

        session = sessionRepo.save(session);

        List<User> users = userRepo.findAllById(sessionRequest.getUserIds());
        for (User user : users) {
            SessionUser su = new SessionUser();
            su.setSession(session);
            su.setUser(user);
            sessionUserRepo.save(su);
        }

        if (sessionRequest.getExercises() != null && !sessionRequest.getExercises().isEmpty()) {
            List<Exercise> exercises = sessionRequest.getExercises();
            for (Exercise exercise : exercises) {
                exercise.setSession(session);
                exerciseRepo.save(exercise);
            }
        }


        return session;
    }

    @Transactional
    public Session editSession(SessionRequest sessionRequest, String id) {
        Session originalSession = sessionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Session with id (" + id + ") not found"));

        originalSession.setId(sessionRequest.getId());
        originalSession.setName(sessionRequest.getName());
        originalSession.setDuration(sessionRequest.getDuration());
        originalSession.setActive(sessionRequest.isActive());
        originalSession.setType(sessionRequest.getType());

        originalSession = sessionRepo.save(originalSession);

        if (sessionRequest.getUserIds() != null) {
            // Delete old associations
            sessionUserRepo.deleteAllBySession(originalSession);

            // Re-create associations
            List<User> users = userRepo.findAllById(sessionRequest.getUserIds());
            for (User user : users) {
                SessionUser su = new SessionUser();
                su.setSession(originalSession);
                su.setUser(user);
                sessionUserRepo.save(su);
            }
        }

        return originalSession;

    }

    // TODO implement
    public boolean deleteSession(String id) {
        if (sessionRepo.existsById(id)) {
            sessionRepo.deleteById(id);
            return true;
        } else {
            throw new EntityNotFoundException("Session with id (" + id + ") not found");
        }
    }
}
