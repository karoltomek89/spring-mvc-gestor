package com.kt.springmvc.gestor.controller;

import com.kt.springmvc.gestor.model.dto.MessageDto;
import com.kt.springmvc.gestor.model.dto.UserDto;
import com.kt.springmvc.gestor.service.MessageService;
import com.kt.springmvc.gestor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @GetMapping("/director/messages")
    public ModelAndView getDirectorMessages(@ModelAttribute UserDto userDto) {
        ModelAndView modelAndView = new ModelAndView("directormessages");
        modelAndView.addObject("messageToInsert", new MessageDto());
        modelAndView.addObject("receivedMessageToDelete", new MessageDto());
        modelAndView.addObject("sendedMessageToDelete", new MessageDto());
        modelAndView.addObject("receivedMessages", messageService.getReceivedMessages());
        modelAndView.addObject("sendedMessages", messageService.getSendedMessages());
        modelAndView.addObject("allUsers", userService.getAllUsers());
        return modelAndView;
    }

    @GetMapping("/teacher/messages")
    public ModelAndView getTeacherMessages(@ModelAttribute UserDto userDto) {
        ModelAndView modelAndView = new ModelAndView("teachermessages");
        modelAndView.addObject("messageToInsert", new MessageDto());
        modelAndView.addObject("receivedMessageToDelete", new MessageDto());
        modelAndView.addObject("sendedMessageToDelete", new MessageDto());
        modelAndView.addObject("receivedMessages", messageService.getReceivedMessages());
        modelAndView.addObject("sendedMessages", messageService.getSendedMessages());
        modelAndView.addObject("allUsers", userService.getAllUsers());
        return modelAndView;
    }

    @PostMapping("/teacher/messages")
    public String teacherSendMessage(@RequestParam("action") String action, @ModelAttribute MessageDto messageDto) {
        if ("send".equals(action)) {
            messageService.sendMessage(messageDto);
        }
        if ("deleteReceived".equals(action)) {
            messageService.deleteReceivedMessage(messageDto);
        }
        if ("deleteSended".equals(action)) {
            messageService.deleteSendedMessage(messageDto);
        }
        return "redirect:/teacher/messages";
    }

    @PostMapping("/director/messages")
    public String directorSendMessage(@RequestParam("action") String action, @ModelAttribute MessageDto messageDto) {
        if ("send".equals(action)) {
            messageService.sendMessage(messageDto);
        }
        if ("deleteReceived".equals(action)) {
            messageService.deleteReceivedMessage(messageDto);
        }
        if ("deleteSended".equals(action)) {
            messageService.deleteSendedMessage(messageDto);
        }
        return "redirect:/director/messagess";
    }
}
