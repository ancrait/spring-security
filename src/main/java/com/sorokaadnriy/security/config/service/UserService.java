package com.sorokaadnriy.security.config.service;

import com.sorokaadnriy.security.entity.User;
import com.sorokaadnriy.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public UserService(UserRepository repository, BCryptPasswordEncoder encoder, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
    }


    public User register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public String login(User user) {
       Authentication authentication =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword()));

        if (authentication.isAuthenticated()){
            return "success";
        }
        throw new UsernameNotFoundException("Check password or username again");
    }
}
