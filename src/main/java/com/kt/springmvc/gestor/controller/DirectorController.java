package com.kt.springmvc.gestor.controller;

import com.kt.springmvc.gestor.model.dto.GrupDto;
import com.kt.springmvc.gestor.model.dto.SubjectDto;
import com.kt.springmvc.gestor.model.dto.UserDto;
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

import java.util.List;

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

    @GetMapping("/director/users")
    public ModelAndView getAllUsers() {
        return new ModelAndView("users", "userList", userService.getAllUsers());
    }

    @PostMapping("/director/users")
    public String manageUser(@RequestParam("action") String action, @ModelAttribute("user") UserDto user) {
        if ("activate".equals(action)) {
            userService.activateUser(user.getId());
        }
        if ("delete".equals(action)) {
            userService.deleteUser(user.getId());
        }
        return "redirect:/director/users";
    }

    @GetMapping("/director/subjects")
    public ModelAndView getAllSubjectsWithTeachers() {
        List<UserDto> teachersList = userService.getTeachers();
        List<SubjectDto> subjectsList = subjectService.getAllSubjects();
        ModelAndView modelAndView = new ModelAndView("subjects");
        modelAndView.addObject("subjectToInsert", new SubjectDto());
        modelAndView.addObject("teachersList", teachersList);
        modelAndView.addObject("subjectsList", subjectsList);
        return modelAndView;
    }

    @PostMapping("/director/subjects")
    public String registerSubject(@RequestParam("action") String action, @ModelAttribute SubjectDto subjectDto) {
        if ("add".equals(action)) {
            subjectService.registerSubject(subjectDto);
        }
        if ("delete".equals(action)) {
            subjectService.deleteSubject(subjectDto.getId());
        }
        return "redirect:/director/subjects";
    }

    @GetMapping("/director/grups")
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

    @PostMapping("/director/grups")
    public String registerGrup(@RequestParam("action") String action, @ModelAttribute GrupDto grupDto, @ModelAttribute SubjectDto subjectDto) {
        if ("addgrup".equals(action)) {
            grupService.registerGrup(grupDto);
        }
        if ("addsubjecttogrup".equals(action)) {
            grupService.addSubjectToGrup(grupDto);
        }
        if ("deletesubjectfromgrup".equals(action)) {
            grupService.removeSubjectFromGrup(subjectDto, grupDto);
        }
        if ("deletegrup".equals(action)) {
            grupService.deleteGrup(grupDto.getId());
        }
        return "redirect:/director/grups";
    }

    @GetMapping("/director/students")
    public ModelAndView getAllStudentsWithGrups() {
        List<UserDto> studentsList = userService.getStudents();
        List<GrupDto> grupsList = grupService.getAllGrups();
        ModelAndView modelAndView = new ModelAndView("students");
        modelAndView.addObject("grupToInsert", new GrupDto());
        modelAndView.addObject("grupsList", grupsList);
        modelAndView.addObject("studentsList", studentsList);
        return modelAndView;
    }

    @PostMapping("/director/students")
    public String registerStudent(@RequestParam("action") String action, @ModelAttribute GrupDto grupDto) {
        if ("add".equals(action)) {
            grupService.addStudentToGrup(grupDto);
        }
        if ("delete".equals(action)) {
            grupService.removeStudentFromGrup(grupDto);
        }
        return "redirect:/director/students";
    }

    @GetMapping("/director/info")
    public ModelAndView infoView() {
        ModelAndView modelAndView = new ModelAndView("directorinfo");
        modelAndView.addObject("user", userService.getLoggedUser());
        return modelAndView;
    }

}
