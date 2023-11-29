package com.petarvukcevic.elib.security.jwt.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginDTO {

    private String username;
    private String password;
//    private boolean rememberMe;
}
