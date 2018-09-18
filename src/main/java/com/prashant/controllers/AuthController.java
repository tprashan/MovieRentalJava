package com.prashant.controllers;

import com.prashant.services.AuthService;
import org.springframework.web.bind.annotation.*;

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
    public String addUser(@RequestBody Map<String, String> request) {
        return authService.addUser(request);
    }

    @RequestMapping(
            value = "/authenticateUser",
            method = RequestMethod.POST)
    public Boolean verifyUser(@RequestBody Map<String, String> request) { return authService.isAuthenticated(request); }
}
