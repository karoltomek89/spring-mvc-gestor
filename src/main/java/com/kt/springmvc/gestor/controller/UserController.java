package com.kt.springmvc.gestor.controller;

import com.kt.springmvc.gestor.model.dto.UserDto;
import com.kt.springmvc.gestor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/registeruser")
    public ModelAndView userView() {
        return new ModelAndView("registeruser", "userToInsert", new UserDto());
    }

    @PostMapping("/registeruser")
    public String addUser(@ModelAttribute UserDto userDto) {
        userService.registerUser(userDto);
        return "redirect:/index";

    }
}
