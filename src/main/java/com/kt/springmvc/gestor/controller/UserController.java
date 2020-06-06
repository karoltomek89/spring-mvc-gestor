package com.kt.springmvc.gestor.controller;

import com.kt.springmvc.gestor.model.dto.SubjectDto;
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
    public String registerUser(@ModelAttribute UserDto userDto) {
        System.out.println('\n' + "Dodaję: " + userDto + '\n');
        userService.registerUser(userDto);
        return "redirect:/index";
    }

    @GetMapping("/info")
    public ModelAndView infoView() {
        ModelAndView modelAndView = new ModelAndView("info");
        modelAndView.addObject("user", userService.getLoggedUser());
        return modelAndView;
    }
}
