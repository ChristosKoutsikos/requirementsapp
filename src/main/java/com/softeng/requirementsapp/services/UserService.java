package com.softeng.requirementsapp.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.softeng.requirementsapp.domain.User;
import com.softeng.requirementsapp.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}