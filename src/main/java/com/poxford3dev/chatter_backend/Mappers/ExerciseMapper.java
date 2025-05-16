package com.poxford3dev.chatter_backend.Mappers;

import com.poxford3dev.chatter_backend.Dtos.UserDto;
import com.poxford3dev.chatter_backend.Entity.Exercise;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ExerciseMapper {
    UserDto toDto(Exercise exercise);
}

