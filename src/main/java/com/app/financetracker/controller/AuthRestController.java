package com.app.financetracker.controller;

import com.app.financetracker.dto.UserDTO;
import com.app.financetracker.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("auth")
public class AuthRestController {

    private final UserService userService;

    @PostMapping("/login")
    public UserDTO login(Authentication authentication) {
        return userService.loadUserByUsername(authentication.getName());
    }
}
