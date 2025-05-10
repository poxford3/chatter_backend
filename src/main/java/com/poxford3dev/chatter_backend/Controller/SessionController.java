package com.poxford3dev.chatter_backend.Controller;

import com.poxford3dev.chatter_backend.Dtos.SessionDto;
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

    @GetMapping
    public List<SessionDto> getSessions() {
        return sessionService.getAllSessions()
                .stream()
                .map(SessionMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessionDto> getUserById(@PathVariable("id") String id) {
        var sesh = sessionService.getSessionById(id);
        if (sesh == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(SessionMapper.toDto(sesh));
    }

    @PostMapping("/{id}")
    public SessionDto editSession(@RequestBody Session editedSession, @PathVariable String id) {
        // https://www.baeldung.com/spring-request-param
        sessionService.editSession(editedSession, id);
        return SessionMapper.toDto(editedSession);
    }


    @PostMapping
    public SessionDto createSession(@RequestBody Session newSesh) {
        sessionService.createSession(newSesh);
        return SessionMapper.toDto(newSesh);
    }
}
