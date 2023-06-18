package com.app.financetracker.service;

import com.app.financetracker.dto.UserDTO;
import com.app.financetracker.persistence.User;
import com.app.financetracker.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public UserDTO loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username '" + username + "' not found"));
        return mapper.map(user, UserDTO.class);
    }
}
