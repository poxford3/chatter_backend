package com.poxford3dev.chatter_backend.Controller;

import com.poxford3dev.chatter_backend.Dtos.SessionDto;
import com.poxford3dev.chatter_backend.Dtos.SessionRequest;
import com.poxford3dev.chatter_backend.Entity.Session;
import com.poxford3dev.chatter_backend.Mappers.SessionMapper;
import com.poxford3dev.chatter_backend.Service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sessions")
public class SessionController {

    @Autowired
    private SessionService sessionService;
    @Autowired
    private SessionMapper sessionMapper;

    @GetMapping
    public List<SessionDto> getSessions() {
        return sessionService.getAllSessions()
                .stream()
                .map(sessionMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessionDto> getUserById(@PathVariable("id") String id) {
        var sesh = sessionService.getSessionById(id);
        if (sesh == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sessionMapper.toDto(sesh));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SessionDto> editSession(@RequestBody SessionRequest editedSession, @PathVariable String id) {
        // https://www.baeldung.com/spring-request-param
        Session editSesh = sessionService.editSession(editedSession, id);
        return ResponseEntity.ok(sessionMapper.toDto(editSesh));
    }


    @PostMapping
    public ResponseEntity<SessionDto> createSession(@RequestBody SessionRequest newSesh) {
        Session session = sessionService.createSession(newSesh);
        return ResponseEntity.ok(sessionMapper.toDto(session));
    }
}
