package com.app.financetracker.service;

import com.app.financetracker.dto.Mapper;
import com.app.financetracker.dto.UserDTO;
import com.app.financetracker.persistence.User;
import com.app.financetracker.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {

    private final Mapper modelMapper;
    private final UserRepository userRepository;
    public ResponseEntity<?> loginUser(UserDTO userData){
        System.out.println(userData);
        User user = userRepository.findByUsername(modelMapper.dtoToUser(userData).getUsername());

        if(user.getPassword().equals(modelMapper.dtoToUser(userData).getPassword()))
            return ResponseEntity.ok(user);

        return (ResponseEntity<?>) ResponseEntity.internalServerError();
    }
}
