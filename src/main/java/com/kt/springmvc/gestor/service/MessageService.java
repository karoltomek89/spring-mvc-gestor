package com.kt.springmvc.gestor.service;

import com.kt.springmvc.gestor.model.dto.GrupDto;
import com.kt.springmvc.gestor.model.dto.MessageDto;
import com.kt.springmvc.gestor.model.dto.SubjectDto;
import com.kt.springmvc.gestor.model.dto.UserDto;
import com.kt.springmvc.gestor.model.entity.Grup;
import com.kt.springmvc.gestor.model.entity.Message;
import com.kt.springmvc.gestor.model.entity.Subject;
import com.kt.springmvc.gestor.model.entity.User;
import com.kt.springmvc.gestor.repository.GrupRepository;
import com.kt.springmvc.gestor.repository.MessageRepository;
import com.kt.springmvc.gestor.repository.SubjectRepository;
import com.kt.springmvc.gestor.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class MessageService {

    private ModelMapper modelMapper;
    private SubjectRepository subjectRepository;
    private UserRepository userRepository;
    private GrupRepository grupRepository;
    private MessageRepository messageRepository;

    public MessageService(ModelMapper modelMapper, SubjectRepository subjectRepository, UserRepository userRepository, GrupRepository grupRepository, MessageRepository messageRepository) {
        this.modelMapper = modelMapper;
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
        this.grupRepository = grupRepository;
        this.messageRepository = messageRepository;
    }

    public List<MessageDto> getSendedMessages() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByNameOrderByName(authentication.getName());
        List<MessageDto> messsagesSendedDtoList = messageRepository.findBySender(user.getEmail())
                .stream()
                .filter(m -> m.getDateDeletedSender() == null)
                .map(m -> modelMapper.map(m, MessageDto.class))
                .collect(Collectors.toList());
        return messsagesSendedDtoList;
    }

    public List<MessageDto> getReceivedMessages() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByNameOrderByName(authentication.getName());
        List<MessageDto> messsagesReceivedDtoList = messageRepository.findByReceiver(user.getEmail())
                .stream()
                .filter(m -> m.getDateDeletedReceiver() == null)
                .map(m -> modelMapper.map(m, MessageDto.class))
                .collect(Collectors.toList());
        return messsagesReceivedDtoList;
    }

    public void sendMessage(MessageDto messageDto) {
        Message message = modelMapper.map(messageDto, Message.class);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User sender = userRepository.findByNameOrderByName(authentication.getName());
        message.setSender(sender.getEmail());

        message = messageRepository.save(message);
    }

    public void deleteSendedMessage(MessageDto messageDto) {
        Date date = Date.valueOf(LocalDate.now());
        Message message = messageRepository.findById(messageDto.getId()).get();
        message.setDateDeletedSender(date);
        messageRepository.save(message);
    }

    public void deleteReceivedMessage(MessageDto messageDto) {
        Date date = Date.valueOf(LocalDate.now());
        Message message = messageRepository.findById(messageDto.getId()).get();
        message.setDateDeletedReceiver(date);
        messageRepository.save(message);
    }
}
