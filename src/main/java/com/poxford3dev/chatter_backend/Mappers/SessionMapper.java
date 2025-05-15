package com.poxford3dev.chatter_backend.Mappers;

import com.poxford3dev.chatter_backend.Dtos.SessionDto;
import com.poxford3dev.chatter_backend.Dtos.UserDto;
import com.poxford3dev.chatter_backend.Mappers.UserMapper;
import com.poxford3dev.chatter_backend.Entity.Session;
import com.poxford3dev.chatter_backend.Entity.SessionUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface SessionMapper {

    @Mapping(target = "users", expression = "java(mapUsers(session))")
    SessionDto toDto(Session session);

    default List<UserDto> mapUsers(Session session) {
        if (session.getSessionUsers() == null) {
            return Collections.emptyList();
        }

        return session.getSessionUsers().stream()
                .map(SessionUser::getUser)
                .map(user -> new UserDto(
                        user.getId(),
                        user.getName(),
                        user.getProfilePic(),
                        user.getCreated()
                ))
                .collect(Collectors.toList());
    }
}