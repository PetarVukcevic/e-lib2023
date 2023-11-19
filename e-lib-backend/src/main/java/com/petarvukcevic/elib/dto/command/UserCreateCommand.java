package com.petarvukcevic.elib.dto.command;

import com.petarvukcevic.elib.dto.query.RoleQuery;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserCreateCommand {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
    private Boolean isActive = false;

    private Set<RoleQuery> roles = new HashSet<>();

}
