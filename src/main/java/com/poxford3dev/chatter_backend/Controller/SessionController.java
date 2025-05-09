package com.poxford3dev.chatter_backend.Controller;

import com.poxford3dev.chatter_backend.Entity.Session;
import com.poxford3dev.chatter_backend.Service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sessions")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @GetMapping
    public List<Session> getSessions() {
        return sessionService.getAllSessions();
    }
}
