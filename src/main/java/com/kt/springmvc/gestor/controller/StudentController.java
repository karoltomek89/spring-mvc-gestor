package com.kt.springmvc.gestor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {

    @GetMapping("/student")
    public String indexView() {
        return "student";
    }
}
