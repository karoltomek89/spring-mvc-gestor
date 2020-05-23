package com.kt.springmvc.gestor.service;

import com.kt.springmvc.gestor.model.dto.GrupDto;
import com.kt.springmvc.gestor.model.dto.SubjectDto;
import com.kt.springmvc.gestor.model.entity.Grup;
import com.kt.springmvc.gestor.model.entity.Subject;
import com.kt.springmvc.gestor.model.entity.User;
import com.kt.springmvc.gestor.repository.GrupRepository;
import com.kt.springmvc.gestor.repository.GrupRepository;
import com.kt.springmvc.gestor.repository.SubjectRepository;
import com.kt.springmvc.gestor.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
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

    public void registerGrup(GrupDto grupDto, SubjectDto subjectDto) {
        User user = userRepository.findById(grupDto.getUserId()).get();
        Grup grup = modelMapper.map(grupDto, Grup.class);
        grup.setUser(user);
        grup = grupRepository.save(grup);
    }

    public void addSubjectToGrup(SubjectDto subjectDto, GrupDto grupDto) {
        Subject subject = subjectRepository.findById(grupDto.getTempSubjectId()).get();
        Grup grup = grupRepository.findById(grupDto.getId()).get();
        grup.getSubjects().add(subject);
        grup = grupRepository.save(grup);
    }

    public void removeSubjectFromGrup(SubjectDto subjectDto, GrupDto grupDto) {
        Subject subject = modelMapper.map(subjectDto, Subject.class);
        Grup grup = grupRepository.findById(grupDto.getId()).get();
        grup.getSubjects().remove(subject);
        grup = grupRepository.save(grup);
    }

    public List<GrupDto> getAllGrups() {
        List<GrupDto> grupDtoList = grupRepository.findAll()
                .stream()
                .map(s -> modelMapper.map(s, GrupDto.class))
                .collect(Collectors.toList());
        grupDtoList.stream().forEach((sub) -> {
            sub.setUserName(userRepository.findById(sub.getUserId()).get().getName());
            sub.setUserSurname(userRepository.findById(sub.getUserId()).get().getSurname());
        });
        return grupDtoList;
    }

    public void deleteGrup(Long id) {
        Date date = Date.valueOf(LocalDate.now());
        Grup grup = grupRepository.findById(id).get();
        grup.setDateDeleted(date);
        grupRepository.save(grup);
    }

}
