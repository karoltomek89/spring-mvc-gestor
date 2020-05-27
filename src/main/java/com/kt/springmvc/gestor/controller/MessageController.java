package com.kt.springmvc.gestor.controller;

import com.kt.springmvc.gestor.model.dto.GrupDto;
import com.kt.springmvc.gestor.model.dto.MessageDto;
import com.kt.springmvc.gestor.model.dto.SubjectDto;
import com.kt.springmvc.gestor.model.dto.UserDto;
import com.kt.springmvc.gestor.service.GrupService;
import com.kt.springmvc.gestor.service.MessageService;
import com.kt.springmvc.gestor.service.SubjectService;
import com.kt.springmvc.gestor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @GetMapping("/messages")
    public ModelAndView getMessages(@ModelAttribute UserDto userDto) {
        ModelAndView modelAndView = new ModelAndView("messages");
        modelAndView.addObject("messageToInsert", new MessageDto());
        modelAndView.addObject("receivedMessageToDelete", new MessageDto());
        modelAndView.addObject("sendedMessageToDelete", new MessageDto());
        modelAndView.addObject("receivedMessages", messageService.getReceivedMessages());
        modelAndView.addObject("sendedMessages", messageService.getSendedMessages());
        modelAndView.addObject("allUsers", userService.getAllUsers());
        return modelAndView;
    }

    @PostMapping("/messages")
    public String registerSubject(@RequestParam("action") String action, @ModelAttribute MessageDto messageDto) {
        if ("send".equals(action)) {
            messageService.sendMessage(messageDto);
        }
        if ("deleteReceived".equals(action)) {
            messageService.deleteReceivedMessage(messageDto);
        }
        if ("deleteSended".equals(action)) {
            messageService.deleteSendedMessage(messageDto);
        }
        return "redirect:/messages";
    }
}
