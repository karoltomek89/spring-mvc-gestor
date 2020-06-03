package com.kt.springmvc.gestor.service;

import com.kt.springmvc.gestor.model.dto.GrupDto;
import com.kt.springmvc.gestor.model.dto.SubjectDto;
import com.kt.springmvc.gestor.model.dto.UserDto;
import com.kt.springmvc.gestor.model.entity.Grup;
import com.kt.springmvc.gestor.model.entity.Subject;
import com.kt.springmvc.gestor.model.entity.User;
import com.kt.springmvc.gestor.repository.GrupRepository;
import com.kt.springmvc.gestor.repository.GrupRepository;
import com.kt.springmvc.gestor.repository.SubjectRepository;
import com.kt.springmvc.gestor.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
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

    public void registerGrup(GrupDto grupDto) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
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
        grup.getStudents().add(user);
        grup = grupRepository.save(grup);
    }

    public void removeStudentFromGrup(GrupDto grupDto) {
        User user = userRepository.findById(grupDto.getUserId()).get();
        Grup grup = grupRepository.findById(grupDto.getId()).get();
        grup.getStudents().remove(user);
        grup = grupRepository.save(grup);
    }

    public List<GrupDto> getAllGrups() {
        modelMapper.typeMap(Grup.class, GrupDto.class)
                .addMappings(m -> m.map(src -> src.getUser().getId(), GrupDto::setUserId))
                .addMappings(m -> m.map(src -> src.getStudents(), GrupDto::setStudents));
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
