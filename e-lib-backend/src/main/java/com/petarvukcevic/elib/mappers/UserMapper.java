package com.petarvukcevic.elib.mappers;

import com.petarvukcevic.elib.dto.command.UserCreateCommand;
import com.petarvukcevic.elib.dto.query.UserQuery;
import com.petarvukcevic.elib.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper
{
    @Mapping(target = "id", ignore = true) // Ignore mapping for the id field
    @Mapping(target = "createdAt", ignore = true) // Ignore mapping for createdAt field
    @Mapping(target = "updatedAt", ignore = true) // Ignore mapping for updatedAt field
    @Mapping(target = "isActive", ignore = true) // Ignore mapping for isActive field
    @Mapping(target = "roles", ignore = true) // Ignore mapping for roles field
    User toEntity(UserCreateCommand userCreateCommand);
    UserQuery toUserQuery(User user);
}