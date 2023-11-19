package com.petarvukcevic.elib.controllers;

import com.petarvukcevic.elib.dto.query.RoleQuery;
import com.petarvukcevic.elib.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleQuery>> findAll()
    {
        List<RoleQuery> roles = roleService.findAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }
}
