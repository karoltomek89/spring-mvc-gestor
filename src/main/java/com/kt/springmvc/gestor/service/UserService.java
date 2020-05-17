package com.kt.springmvc.gestor.service;

import com.kt.springmvc.gestor.model.dto.UserDto;
import com.kt.springmvc.gestor.model.entity.User;
import com.kt.springmvc.gestor.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public UserService(ModelMapper modelMapper, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode((user.getPassword())));
        user = userRepository.save(user);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    }

}
