package com.sorokaadnriy.security.config.service;

import com.sorokaadnriy.security.CustomUserDetails;
import com.sorokaadnriy.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.sorokaadnriy.security.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found" + username));

        return new CustomUserDetails(user);
    }
}
