package com.petarvukcevic.elib.controllers;

import com.petarvukcevic.elib.dto.command.UserCreateCommand;
import com.petarvukcevic.elib.dto.query.UserQuery;
import com.petarvukcevic.elib.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody UserCreateCommand userCreateCommand)
    {
        userService.create(userCreateCommand);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    // @PreAuthorize("@customAuth.hasAuthKey(#authKey)")
    public ResponseEntity<List<UserQuery>> all()
    {
        List<UserQuery> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "exists-by-username/{username}")
    public ResponseEntity<Map<String, Boolean>> existsByUsername(@PathVariable String username)
    {
        boolean doesUsernameExists = userService.existsByUsername(username);

        Map<String, Boolean> response = Map.of("usernameExists", doesUsernameExists);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
