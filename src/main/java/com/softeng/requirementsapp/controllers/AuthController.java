package com.softeng.requirementsapp.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.softeng.requirementsapp.domain.User;
import com.softeng.requirementsapp.dto.RegisterRequest;
import com.softeng.requirementsapp.enums.Role;
import com.softeng.requirementsapp.services.UserService;

@Controller
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }
    
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
    
    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(RegisterRequest registerRequest, Model model) {

        if (userService.findByEmail(registerRequest.getEmail()).isPresent()) {
            model.addAttribute("errorMessage", "A user with this email already exists.");
            return "register";
        }

        User user = new User();
        user.setFullName(registerRequest.getFullName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(Role.DEVELOPER);
        user.setEnabled(true);

        userService.saveUser(user);

        return "redirect:/login?registered";
    }
}