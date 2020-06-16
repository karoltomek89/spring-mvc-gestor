package com.kt.springmvc.gestor.controller;

import com.kt.springmvc.gestor.model.dto.GradeDto;
import com.kt.springmvc.gestor.model.dto.GrupDto;
import com.kt.springmvc.gestor.model.dto.SubjectDto;
import com.kt.springmvc.gestor.model.dto.UserDto;
import com.kt.springmvc.gestor.service.GradeService;
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

import java.util.ArrayList;
import java.util.List;

@Controller
public class TeacherController {

    private final String DELETE = "delete";
    private final String ADD = "add";

    @Autowired
    private UserService userService;

    @Autowired
    private GrupService grupService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private GradeService gradeService;

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
        if (ADD.equals(action)) {
            userService.addParent(userDto);
        }
        if (DELETE.equals(action)) {
            userService.deleteParent(userDto);
        }
        return "redirect:/teacher/parents";
    }

    @GetMapping("/teacher/grupssubjects")
    public ModelAndView getAllGrupsWithSubjects() {
        List<GrupDto> grupsList = grupService.getAllGrupsOfTeacher();
        List<SubjectDto> subjectsList = subjectService.getAllSubjectsOfTeacher();
        ModelAndView modelAndView = new ModelAndView("teachergrupssubjects");
        modelAndView.addObject("subjectsList", subjectsList);
        modelAndView.addObject("grupsList", grupsList);
        return modelAndView;
    }

    @GetMapping("/teacher/grades")
    public ModelAndView getAllGradesOfGrup(@RequestParam("subjectId") String subjectId, @RequestParam("grupId") String grupId) {
        GrupDto studentsGrupList = grupService.findGrupById(Long.valueOf(grupId));
        ModelAndView modelAndView = new ModelAndView("teachergrades");
        modelAndView.addObject("gradeToInsert", new GradeDto());
        modelAndView.addObject("studentsGrupList", studentsGrupList);
        return modelAndView;
    }

    @PostMapping("/teacher/grades")
    public String addGrade(@RequestParam("action") String action, @ModelAttribute GradeDto gradeDto) {
        if (ADD.equals(action)) {
            gradeService.addGradeToSubject(gradeDto);
        }
        return "redirect:/teacher/grades";
    }
}
