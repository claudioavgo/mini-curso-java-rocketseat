package com.claudioav.todolist.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.claudioav.todolist.models.UserModel;
import com.claudioav.todolist.repositories.UserRepository;
import com.claudioav.todolist.services.UserService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity create(@RequestBody UserModel newUser) {
        String newPassword = BCrypt.withDefaults().hashToString(12, newUser.getPassword().toCharArray());
        newUser.setPassword(newPassword);

        UserModel userObject = userService.create(newUser);
        if (userObject != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(userObject);
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("{\"success\":0, \"cause\": \"User already exist.\"}");
        }
    }
}
