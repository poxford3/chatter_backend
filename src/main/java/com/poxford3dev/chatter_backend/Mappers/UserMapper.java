package com.poxford3dev.chatter_backend.Mappers;

import com.poxford3dev.chatter_backend.Dtos.UserDto;
import com.poxford3dev.chatter_backend.Entity.User;


public class UserMapper {
    public static UserDto toDto(User user) {
        if ( user == null ) {
            return null;
        }

        Integer id = null;
        String name = null;
        String profilePic = null;

        id = user.getId();
        name = user.getName();
        profilePic = user.getProfilePic();

        return new UserDto(id, name, profilePic);
    }
}
