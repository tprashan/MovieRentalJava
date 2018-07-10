package com.prashant.services;

import com.prashant.model.User;
import com.prashant.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {

    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(Map<String, String> req) {
        User user = new User(req.get("uname"),req.get("email"),req.get("password"));
        userRepository.save(user);
    }
}
