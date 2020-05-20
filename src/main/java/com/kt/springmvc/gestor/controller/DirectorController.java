package com.kt.springmvc.gestor.controller;

import com.kt.springmvc.gestor.model.dto.UserDto;
import com.kt.springmvc.gestor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DirectorController {

    @Autowired
    private UserService userService;

    @GetMapping("/director")
    public String indexView() {
        return "director";
    }

    @GetMapping("/users")
    public ModelAndView getAllUsers() {
        return new ModelAndView("users", "userList", userService.getAllUsers());
    }

    @PostMapping("/manageusers")
    public String deleteUser(@RequestParam("action") String action, @ModelAttribute("user") UserDto user) {
        if ("activate".equals(action)) {
            userService.activateUser(user.getId());
        }
        if ("delete".equals(action)) {
            userService.deleteUser(user.getId());
        }
        return "redirect:/users";
    }
}
