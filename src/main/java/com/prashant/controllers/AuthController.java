package com.prashant.controllers;

import com.prashant.services.AuthService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(
            value = "/signup",
            method = RequestMethod.POST)
    public void process(@RequestBody Map<String, String> request) {
        authService.addUser(request);
    }
}
