package com.mathschool.MathSchoolBack.services;

import com.mathschool.MathSchoolBack.entities.*;
import com.mathschool.MathSchoolBack.model.*;
import com.mathschool.MathSchoolBack.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceRetrieveData {

    @Autowired
    private SchoolYearEntityRepository schoolYearEntityRepository;
    @Autowired
    private SubjectEntityRepository subjectEntityRepository;
    @Autowired
    private StudentEntityRepository studentEntityRepository;

    @Autowired
    private ClassEntityRepository classEntityRepository;
    @Autowired
    private WatchedClassEntityRepository watchedClassEntityRepository;
    @Autowired
    private CompletedTestEntityRepository completedTestEntityRepository;

    public List<SchoolYearEntity> getYears(){
        return schoolYearEntityRepository.findAll();
    }
    public List<SubjectEntity> getSubjects(){return subjectEntityRepository.findAll();}

    public List<SubjectEntity> getSubjectsByYear(String year){
        return subjectEntityRepository.findBySchoolYearEntityName(year);
    }

    public Student getStudent (Integer id){
        StudentEntity studentEntity=studentEntityRepository.findById(id).get();
        return new Student(studentEntity.getId(),studentEntity.getName(),studentEntity.getFirebase_id());
    }

    public List<WatchedClassEntity> getWatchedClasses(Integer studentId){
        return watchedClassEntityRepository.findByStudentId(studentId);
    }

    public List<Lesson> getClassBySubject(String subjectName){
        Optional<SubjectEntity> subjectEntity=subjectEntityRepository.findByName(subjectName);
        if (subjectEntity.isEmpty()) return null;
        return subjectEntityRepository.findByName(subjectName).get().getClassEntitySet().stream()
                .map(c->new Lesson(c.getSubject().getName(),new Subject(c.getSubject().getName(),new SchoolYear(c.getSubject().getSchoolYearEntity().getName())),
                        Arrays.stream(c.getContent_urls().split(",")).toList())).collect(Collectors.toList());
    }


    public List<Test> getTestBySubject(String subjectName) {
        Optional<SubjectEntity> subjectEntity=subjectEntityRepository.findByName(subjectName);
        if (subjectEntity.isEmpty()) return null;
        return subjectEntityRepository.findByName(subjectName).get().getTestEntitySet().stream()
                .map(c->new Test(new Subject(c.getSubject().getName(),new SchoolYear(c.getSubject().getSchoolYearEntity().getName())),
                        c.getQuestionEntitySet().stream().map(
                                q->new Question(q.getEnunciado(),new String[]{q.getAlternativa1(),q.getAlternativa2(),q.getAlternativa3(),q.getAlternativa4()},q.getIndexRespostaCorreta()))
                                .collect(Collectors.toList()))).collect(Collectors.toList());
    }

    public List<CompletedTestEntity> getCompletedTests(Integer studentId) {
        return completedTestEntityRepository.findByStudentId(studentId);
    }
}
