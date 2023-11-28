package com.petarvukcevic.elib.security.controllers;

import com.petarvukcevic.elib.security.jwt.JwtTokenProvider;
import com.petarvukcevic.elib.security.jwt.dto.JwtTokenDTO;
import com.petarvukcevic.elib.security.jwt.dto.LoginDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/authenticate")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO)
    {
        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    loginDTO.getUsername(),
                    loginDTO.getPassword()
            );
            Authentication auth = authenticationManager.authenticate(authentication);
            log.info("Authentication after successful login: {}", auth);

            JwtTokenDTO token = tokenProvider.generateToken(auth, loginDTO.isRememberMe());
            return new ResponseEntity<>(token, HttpStatus.CREATED);
        }
        catch (Exception e) {
            log.error("Error occured on login. Message {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
