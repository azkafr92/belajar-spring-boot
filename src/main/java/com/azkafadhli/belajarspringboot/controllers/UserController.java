package com.azkafadhli.belajarspringboot.controllers;

import com.azkafadhli.belajarspringboot.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

}
