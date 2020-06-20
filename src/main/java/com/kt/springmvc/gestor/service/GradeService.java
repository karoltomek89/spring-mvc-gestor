package com.kt.springmvc.gestor.service;

import com.kt.springmvc.gestor.model.dto.GradeDto;
import com.kt.springmvc.gestor.model.dto.GrupDto;
import com.kt.springmvc.gestor.model.entity.Grade;
import com.kt.springmvc.gestor.model.entity.Grup;
import com.kt.springmvc.gestor.model.entity.Subject;
import com.kt.springmvc.gestor.model.entity.User;
import com.kt.springmvc.gestor.repository.GradeRepository;
import com.kt.springmvc.gestor.repository.GrupRepository;
import com.kt.springmvc.gestor.repository.SubjectRepository;
import com.kt.springmvc.gestor.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class GradeService {

    private ModelMapper modelMapper;
    private SubjectRepository subjectRepository;
    private UserRepository userRepository;
    private GrupRepository grupRepository;
    private GradeRepository gradeRepository;

    public GradeService(ModelMapper modelMapper, SubjectRepository subjectRepository, UserRepository userRepository, GrupRepository grupRepository, GradeRepository gradeRepository) {
        this.modelMapper = modelMapper;
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
        this.grupRepository = grupRepository;
        this.gradeRepository = gradeRepository;
    }

    public void addGradeToSubject(GradeDto gradeDto) {

        User user = userRepository.findById(gradeDto.getStudentId()).get();
        Grade grade = modelMapper.map(gradeDto, Grade.class);
        grade.setUser(user);
        grade = gradeRepository.save(grade);
    }

    public List<GradeDto> getAllGradesOfSubject(Long subjectId, Long studentId) {
        List<GradeDto> allGradesOfSubject = gradeRepository.findByUserIdAndSubjectId(studentId, subjectId)
                .stream()
                .map(s -> modelMapper.map(s, GradeDto.class))
                .collect(Collectors.toList());
        return allGradesOfSubject;

    }

}
