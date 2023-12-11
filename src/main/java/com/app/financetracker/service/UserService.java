package com.app.financetracker.service;

import com.app.financetracker.utils.Mapper;
import com.app.financetracker.dto.UserDTO;
import com.app.financetracker.persistence.User;
import com.app.financetracker.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final Mapper customMapper;

    public UserDTO loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username '" + username + "' not found"));
        return customMapper.userToDTO(user);
    }

    public UserDTO addAccount(UserDTO userDTO) {
        User user = userRepository.save(customMapper.dtoToUser(userDTO));
        return customMapper.userToDTO(user);
    }
}
