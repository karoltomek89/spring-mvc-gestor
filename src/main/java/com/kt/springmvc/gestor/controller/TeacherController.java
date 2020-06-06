package com.kt.springmvc.gestor.controller;

import com.kt.springmvc.gestor.model.dto.GrupDto;
import com.kt.springmvc.gestor.model.dto.SubjectDto;
import com.kt.springmvc.gestor.model.dto.UserDto;
import com.kt.springmvc.gestor.service.GrupService;
import com.kt.springmvc.gestor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TeacherController {

    @Autowired
    private UserService userService;

    @Autowired
    private GrupService grupService;

    @GetMapping("/teacher")
    public String indexView() {
        return "teacher";
    }

    @GetMapping("/teacher/info")
    public ModelAndView infoView() {
        ModelAndView modelAndView = new ModelAndView("teacherinfo");
        modelAndView.addObject("user", userService.getLoggedUser());
        return modelAndView;
    }

    @GetMapping("/teacher/parents")
    public ModelAndView getAllGrupsWithTeachers() {
        List<GrupDto> grupsList = grupService.getAllGrupsOfTeacher();
        List<UserDto> parentsList = userService.getParents();
        List<UserDto> studentsList = grupService.getAllStudentsOfTeacher();
        ModelAndView modelAndView = new ModelAndView("parents");
        modelAndView.addObject("userToInsert", new UserDto());
        modelAndView.addObject("parentsList", parentsList);
        modelAndView.addObject("grupsList", grupsList);
        modelAndView.addObject("studentsList", studentsList);
        return modelAndView;
    }

    @PostMapping("/teacher/parents")
    public String registerGrup(@RequestParam("action") String action, @ModelAttribute UserDto userDto) {
        if ("addparent".equals(action)) {
            userService.addParent(userDto);
        }
        if ("removeparent".equals(action)) {
            userService.deleteParent(userDto);
        }
        return "redirect:/teacher/parents";
    }
}
