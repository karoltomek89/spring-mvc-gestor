package com.kt.springmvc.gestor.service;

import com.kt.springmvc.gestor.model.dto.SubjectDto;
import com.kt.springmvc.gestor.model.dto.SubjectDto;
import com.kt.springmvc.gestor.model.entity.Subject;
import com.kt.springmvc.gestor.model.entity.User;
import com.kt.springmvc.gestor.repository.SubjectRepository;
import com.kt.springmvc.gestor.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class SubjectService {

    private ModelMapper modelMapper;
    private SubjectRepository subjectRepository;
    private UserRepository userRepository;

    public SubjectService(ModelMapper modelMapper, SubjectRepository subjectRepository, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
    }

    public void registerSubject(SubjectDto subjectDto) {
        Subject subject = modelMapper.map(subjectDto, Subject.class);
        User user = userRepository.findById(subjectDto.getUserId()).get();
        subject.setUser(user);
        subject = subjectRepository.save(subject);
    }

    public List<SubjectDto> getAllSubjects() {
        List<SubjectDto> subjectDtoList = subjectRepository.findAll()
                .stream()
                .map(s -> modelMapper.map(s, SubjectDto.class))
                .collect(Collectors.toList());
        subjectDtoList.stream().forEach((sub) -> sub.setUserSurname(userRepository.findById(sub.getUserId()).get().getName()));
        return subjectDtoList;
    }

    public void deleteSubject(Long id) {
        Date date = Date.valueOf(LocalDate.now());
        Subject subject = subjectRepository.findById(id).get();
        subject.setDateDeleted(date);
        subjectRepository.save(subject);
    }

}
