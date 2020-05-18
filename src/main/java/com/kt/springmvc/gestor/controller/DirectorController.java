package com.kt.springmvc.gestor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DirectorController {

    @GetMapping("/director")
    public String indexView() {
        return "director";
    }
}
