package com.app.financetracker.controller;

import com.app.financetracker.dto.ExpenseDTO;
import com.app.financetracker.dto.UserDTO;
import com.app.financetracker.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("auth")
public class AuthRestController {

    private final UserService userService;

    @PostMapping("/login")
    public UserDTO login(Authentication authentication) {return userService.loadUserByUsername(authentication.getName());
    }

    @ResponseBody
    @PostMapping("/users")
    public UserDTO addUser(@RequestBody UserDTO userDTO){
        return userService.addAccount(userDTO);
    }

    @ResponseBody
    @GetMapping("/data")
    public UserDTO getUserData(Authentication authentication){
        return userService.loadUserByUsername(authentication.getName());
    }

}


