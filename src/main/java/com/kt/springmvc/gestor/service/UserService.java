package com.kt.springmvc.gestor.service;

import com.kt.springmvc.gestor.model.dto.UserDto;
import com.kt.springmvc.gestor.model.entity.User;
import com.kt.springmvc.gestor.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;


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

    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(u -> modelMapper.map(u, UserDto.class))
                .collect(Collectors.toList());
    }

    public List<UserDto> getTeachers() {
        return userRepository.findByRoleOrderByRole("ROLE_TEACHER")
                .stream()
                .map(u -> modelMapper.map(u, UserDto.class))
                .collect(Collectors.toList());
    }

    public List<UserDto> getStudents() {
        return userRepository.findByRoleOrderByRole("ROLE_STUDENT")
                .stream()
                .map(u -> modelMapper.map(u, UserDto.class))
                .collect(Collectors.toList());
    }

    public List<UserDto> getParents() {
        return userRepository.findByRoleOrderByRole("ROLE_PARENT")
                .stream()
                .map(u -> modelMapper.map(u, UserDto.class))
                .collect(Collectors.toList());
    }

    public void activateUser(Long id) {
        User user = userRepository.findById(id).get();
        user.setActive(true);
        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        Date date = Date.valueOf(LocalDate.now());
        User user = userRepository.findById(id).get();
        user.setDateDeleted(date);
        userRepository.save(user);
    }

    public UserDto getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByNameOrderByName(authentication.getName());
        return modelMapper.map(user, UserDto.class);
    }

    public void addParent(UserDto userDto) {
        User student = userRepository.findById(userDto.getId()).get();
        User parent = userRepository.findById(userDto.getTempParentId()).get();
        student.getParents().add(parent);
        student = userRepository.save(student);

    }

    public void deleteParent(UserDto userDto) {
        User student = userRepository.findById(userDto.getId()).get();
        User parent = userRepository.findById(userDto.getTempParentId()).get();
        student.getParents().remove(parent);
        student = userRepository.save(student);

    }

}
