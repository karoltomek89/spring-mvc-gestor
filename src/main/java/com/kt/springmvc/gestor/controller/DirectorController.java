package com.kt.springmvc.gestor.controller;

import com.kt.springmvc.gestor.model.dto.GrupDto;
import com.kt.springmvc.gestor.model.dto.SubjectDto;
import com.kt.springmvc.gestor.model.dto.UserDto;
import com.kt.springmvc.gestor.model.entity.User;
import com.kt.springmvc.gestor.service.GrupService;
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

    @Autowired
    private GrupService grupService;

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
    public String registerSubject(@RequestParam("action") String action, @ModelAttribute SubjectDto subjectDto) {
        if ("add".equals(action)) {
            subjectService.registerSubject(subjectDto);
        }
        if ("delete".equals(action)) {
            subjectService.deleteSubject(subjectDto.getId());
        }
        return "redirect:/subjects";
    }

    @GetMapping("/grups")
    public ModelAndView getAllGrupsWithTeachers() {
        List<UserDto> teachersList = userService.getTeachers();
        List<SubjectDto> subjectsList = subjectService.getAllSubjects();
        List<GrupDto> grupsList = grupService.getAllGrups();
        ModelAndView modelAndView = new ModelAndView("grups");
        modelAndView.addObject("grupToInsert", new GrupDto());
        modelAndView.addObject("teachersList", teachersList);
        modelAndView.addObject("subjectsList", subjectsList);
        modelAndView.addObject("grupsList", grupsList);
        return modelAndView;
    }

    @PostMapping("/grups")
    public String registerGrup(@RequestParam("action") String action, @ModelAttribute GrupDto grupDto, @ModelAttribute SubjectDto subjectDto) {
        if ("addgrup".equals(action)) {
            grupService.registerGrup(grupDto, subjectDto);
        }
        if ("addsubjecttogrup".equals(action)) {
            grupService.addSubjectToGrup(subjectDto, grupDto);
        }
        if ("deletesubjectfromgrup".equals(action)) {
            grupService.removeSubjectFromGrup(subjectDto, grupDto);
        }
        return "redirect:/grups";
    }
}
