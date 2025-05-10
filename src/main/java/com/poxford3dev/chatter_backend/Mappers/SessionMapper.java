package com.poxford3dev.chatter_backend.Mappers;

import com.poxford3dev.chatter_backend.Dtos.SessionDto;
import com.poxford3dev.chatter_backend.Entity.Session;

import java.sql.Date;
import java.sql.Timestamp;

public class SessionMapper {

    public static SessionDto toDto(Session session) {
        if ( session == null ) {
            return null;
        }

        String id = null;
        String name = null;
        Integer duration = null;
        Date date = null;
        boolean active = false;
        String type = null;

        id = session.getId();
        name = session.getName();
        duration = session.getDuration();
        date = session.getDate();
//        long now = System.currentTimeMillis();
//        Timestamp date = new Timestamp(now);
        active = session.isActive();
        type = session.getType();

        return new SessionDto(id, name, duration, date, active, type);
    }
}
