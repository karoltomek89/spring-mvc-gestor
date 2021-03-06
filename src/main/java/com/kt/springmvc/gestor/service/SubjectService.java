package com.kt.springmvc.gestor.service;

import com.kt.springmvc.gestor.model.dto.GrupDto;
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

    public SubjectDto fillSubjectsByNameAndSurname(SubjectDto sub) {
        User user = userRepository.findById(sub.getUserId()).get();
        sub.setUserName(user.getName());
        sub.setUserSurname(user.getSurname());
        return sub;
    }

    public List<SubjectDto> getAllSubjects() {
        return subjectRepository.findAll()
                .stream()
                .map(s -> modelMapper.map(s, SubjectDto.class))
                .map(this::fillSubjectsByNameAndSurname)
                .collect(Collectors.toList());
    }

    public void deleteSubject(Long id) {
        Date date = Date.valueOf(LocalDate.now());
        Subject subject = subjectRepository.findById(id).get();
        subject.setDateDeleted(date);
        subjectRepository.save(subject);
    }

    public List<SubjectDto> getAllSubjectsOfTeacher() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByNameOrderByName(authentication.getName());
        List<SubjectDto> allSubjectsOfTeacher = subjectRepository.findByUserId(user.getId())
                .stream()
                .map(s -> modelMapper.map(s, SubjectDto.class))
                .collect(Collectors.toList());
        return allSubjectsOfTeacher;

    }

}
