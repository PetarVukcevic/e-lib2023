package com.petarvukcevic.elib.dto.query;

import lombok.Data;

@Data
public class UserQuery {

    private Integer id;
    private String username;
    private String firstName;
    private String lastName;
    private Boolean isActive;
}
