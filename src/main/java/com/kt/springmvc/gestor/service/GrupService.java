package com.kt.springmvc.gestor.service;

import com.kt.springmvc.gestor.model.dto.GrupDto;
import com.kt.springmvc.gestor.model.dto.SubjectDto;
import com.kt.springmvc.gestor.model.dto.UserDto;
import com.kt.springmvc.gestor.model.entity.Grup;
import com.kt.springmvc.gestor.model.entity.Subject;
import com.kt.springmvc.gestor.model.entity.User;
import com.kt.springmvc.gestor.repository.GrupRepository;
import com.kt.springmvc.gestor.repository.SubjectRepository;
import com.kt.springmvc.gestor.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class GrupService {

    private ModelMapper modelMapper;
    private SubjectRepository subjectRepository;
    private UserRepository userRepository;
    private GrupRepository grupRepository;

    public GrupService(ModelMapper modelMapper, SubjectRepository subjectRepository, UserRepository userRepository, GrupRepository grupRepository) {
        this.modelMapper = modelMapper;
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
        this.grupRepository = grupRepository;
    }

    public void registerGrup(GrupDto grupDto) {
        User user = userRepository.findById(grupDto.getUserId()).get();
        Grup grup = modelMapper.map(grupDto, Grup.class);
        grup.setUser(user);
        grup = grupRepository.save(grup);
    }

    public void addSubjectToGrup(GrupDto grupDto) {
        Subject subject = subjectRepository.findById(grupDto.getTempSubjectId()).get();
        Grup grup = grupRepository.findById(grupDto.getId()).get();
        grup.getSubjects().add(subject);
        grup = grupRepository.save(grup);
    }

    public void removeSubjectFromGrup(SubjectDto subjectDto, GrupDto grupDto) {
        Subject subject = subjectRepository.findById(subjectDto.getId()).get();
        Grup grup = grupRepository.findById(grupDto.getId()).get();
        grup.getSubjects().remove(subject);
        grup = grupRepository.save(grup);
    }

    public void addStudentToGrup(GrupDto grupDto) {
        User user = userRepository.findById(grupDto.getTempStudentId()).get();
        Grup grup = grupRepository.findById(grupDto.getId()).get();
        user.setStudentGrup(grup);
        user = userRepository.save(user);
    }

    public void removeStudentFromGrup(GrupDto grupDto) {
        User user = userRepository.findById(grupDto.getUserId()).get();
        Grup grup = grupRepository.findById(grupDto.getId()).get();
        grup.getStudents().remove(user);
        grup = grupRepository.save(grup);
    }


    public GrupDto fillGrupsByNameAndSurname(GrupDto grup) {
        User user = userRepository.findById(grup.getUserId()).get();
        grup.setUserName(user.getName());
        grup.setUserSurname(user.getSurname());
        return grup;
    }

    public List<GrupDto> getAllGrups() {
        List<GrupDto> allGrups = grupRepository.findAll()
                .stream()
                .map(s -> modelMapper.map(s, GrupDto.class))
                .map(this::fillGrupsByNameAndSurname)
                .collect(Collectors.toList());
        return allGrups;
    }

    public void deleteGrup(Long id) {
        Date date = Date.valueOf(LocalDate.now());
        Grup grup = grupRepository.findById(id).get();
        grup.setDateDeleted(date);
        grupRepository.save(grup);
    }

    public List<GrupDto> getAllGrupsOfTeacher() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByNameOrderByName(authentication.getName());
        List<GrupDto> allGrupsOfTeacher = grupRepository.findByUserId(user.getId())
                .stream()
                .map(s -> modelMapper.map(s, GrupDto.class))
                .collect(Collectors.toList());
        return allGrupsOfTeacher;

    }

    public List<UserDto> getAllStudentsOfTeacher() {
        Set<User> users = new HashSet<>();
        for (GrupDto grup : getAllGrupsOfTeacher()
        ) {
            users.addAll(grup.getStudents());
        }
        List<UserDto> allStudents = users.stream()
                .map(s -> modelMapper.map(s, UserDto.class))
                .collect(Collectors.toList());
        return allStudents;
    }

}
