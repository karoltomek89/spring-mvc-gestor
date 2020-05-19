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
public class DirectorController {

    @Autowired
    private UserService userService;

    @GetMapping("/director")
    public String indexView() {
        return "director";
    }

    @GetMapping("/accounts")
    public ModelAndView getAllUsers() {
        return new ModelAndView("accounts", "userList", userService.getAllUsers());
    }

    @PostMapping("/activateaccount")
    public String deleteUser(@ModelAttribute("user") UserDto user) {
        userService.activateUser(user.getId());
        return "redirect:/accounts";
    }
}
