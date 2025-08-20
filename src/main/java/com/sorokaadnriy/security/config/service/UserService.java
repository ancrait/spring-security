package com.sorokaadnriy.security.config.service;

import com.sorokaadnriy.security.entity.User;
import com.sorokaadnriy.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository repository;
    private final BCryptPasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository repository, BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }


    public User register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public String login(User user) {
        if (repository.findByUserName(user.getUserName()).isPresent()){
            return "success";
        }
        throw new UsernameNotFoundException("Check password or username again");
    }
}
