package com.petarvukcevic.elib.security.jwt.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class JwtTokenDTO {

    private final String accessToken;
    private final String refreshToken;

}