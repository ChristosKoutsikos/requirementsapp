package com.softeng.requirementsapp.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.softeng.requirementsapp.domain.User;
import com.softeng.requirementsapp.services.UserService;

@Controller
public class ProfileController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public ProfileController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/profile")
    public String showProfile(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@AuthenticationPrincipal User loggedUser,
                                String fullName,
                                String email,
                                String password,
                                Model model) {

        if (!loggedUser.getEmail().equals(email) && userService.findByEmail(email).isPresent()) {
            model.addAttribute("user", loggedUser);
            model.addAttribute("errorMessage", "This email is already used by another account.");
            return "profile";
        }

        loggedUser.setFullName(fullName);
        loggedUser.setEmail(email);

        if (password != null && !password.isBlank()) {
            loggedUser.setPassword(passwordEncoder.encode(password));
        }

        userService.saveUser(loggedUser);

        model.addAttribute("user", loggedUser);
        model.addAttribute("successMessage", "Profile updated successfully.");

        return "profile";
    }
}