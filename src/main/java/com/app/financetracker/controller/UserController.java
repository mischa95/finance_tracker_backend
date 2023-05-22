package com.app.financetracker.controller;

import com.app.financetracker.dto.ExpenseDTO;
import com.app.financetracker.dto.UserDTO;
import com.app.financetracker.persistence.User;
import com.app.financetracker.repository.UserRepository;
import com.app.financetracker.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController {

    private final UserService userService;
//
//    @Autowired
//    private final UserRepository userRepository;
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDTO userData){
        return userService.loginUser(userData);
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> loginUser(@RequestBody User userData){
//        System.out.println(userData.getUsername());
//        User user = userRepository.findByUsername(userData.getUsername());
//
//        if(user.getPassword().equals(userData.getPassword()))
//            return ResponseEntity.ok(user);
//
//        return (ResponseEntity<?>) ResponseEntity.internalServerError();
//    }
}
