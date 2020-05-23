package com.kt.springmvc.gestor.controller;

import com.kt.springmvc.gestor.model.dto.SubjectDto;
import com.kt.springmvc.gestor.model.dto.UserDto;
import com.kt.springmvc.gestor.model.entity.User;
import com.kt.springmvc.gestor.service.SubjectService;
import com.kt.springmvc.gestor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DirectorController {

    @Autowired
    private UserService userService;

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/director")
    public String indexView() {
        return "director";
    }

    @GetMapping("/users")
    public ModelAndView getAllUsers() {
        return new ModelAndView("users", "userList", userService.getAllUsers());
    }

    @PostMapping("/manageusers")
    public String manageUser(@RequestParam("action") String action, @ModelAttribute("user") UserDto user) {
        if ("activate".equals(action)) {
            userService.activateUser(user.getId());
        }
        if ("delete".equals(action)) {
            userService.deleteUser(user.getId());
        }
        return "redirect:/users";
    }

    @GetMapping("/subjects")
    public ModelAndView getAllSubjectsWithTeachers() {
        List<UserDto> teachersList = userService.getTeachers();
        List<SubjectDto> subjectsList = subjectService.getAllSubjects();
        ModelAndView modelAndView = new ModelAndView("subjects");
        modelAndView.addObject("subjectToInsert", new SubjectDto());
        modelAndView.addObject("teachersList", teachersList);
        modelAndView.addObject("subjectsList", subjectsList);
        return modelAndView;
    }

    @PostMapping("/subjects")
    public String registerSubject(@ModelAttribute SubjectDto subjectDto) {
        subjectService.registerSubject(subjectDto);
        return "redirect:/subjects";
    }

}
