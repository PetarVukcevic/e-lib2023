package com.petarvukcevic.elib.mappers;

import com.petarvukcevic.elib.dto.query.RoleQuery;
import com.petarvukcevic.elib.entities.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role toRole(RoleQuery role);
    RoleQuery toRoleQuery(Role role);
}
