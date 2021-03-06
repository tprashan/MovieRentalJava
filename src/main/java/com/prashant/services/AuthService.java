package com.prashant.services;

import com.prashant.model.User;
import com.prashant.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AuthService {

    private final UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String addUser(Map<String, String> req) {
        User user = new User(req.get("uname"),req.get("email"),req.get("password"));
        try {
            userRepository.save(user);
        }
        catch(DataIntegrityViolationException e){
            System.out.println("User already exist");
            return "Not Saved";
        }
        return "Saved";
    }

    public Boolean isAuthenticated(Map<String, String> request) {
        String userName = request.get("uname");
        String password = request.get("password");
        List<User> users =  userRepository.findUserByUsername(userName);
        return users.size() > 0 && users.get(0).getUsername().equals(userName) && users.get(0).getPassword().equals(password);
    }
}
