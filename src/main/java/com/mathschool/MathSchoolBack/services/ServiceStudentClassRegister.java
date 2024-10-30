package com.mathschool.MathSchoolBack.services;

import com.mathschool.MathSchoolBack.entities.*;
import com.mathschool.MathSchoolBack.entities.key.CompletedTestEntityKey;
import com.mathschool.MathSchoolBack.entities.key.WatchedClassEntityKey;
import com.mathschool.MathSchoolBack.model.*;
import com.mathschool.MathSchoolBack.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class ServiceStudentClassRegister {

    @Autowired
    private SubjectEntityRepository subjectEntityRepository;
    @Autowired
    private ClassEntityRepository classEntityRepository;
    @Autowired
    private TestEntityRepository testEntityRepository;
    @Autowired
    private WatchedClassEntityRepository watchedClassEntityRepository;
    @Autowired
    private StudentEntityRepository studentEntityRepository;
    @Autowired
    private CompletedTestEntityRepository completedTestEntityRepository;

    public Lesson addClass(String subjectName, String className, String urls) {
        Optional<SubjectEntity> subjectEntityOptional=subjectEntityRepository.findByName(subjectName);
        SubjectEntity subjectEntity=subjectEntityOptional.get();
        ClassEntity classEntity=new ClassEntity();
        classEntity.setName(className);
        classEntity.setContent_urls(urls);
        classEntity.setSubject(subjectEntity);
        classEntityRepository.save(classEntity);
        return new Lesson(subjectEntity.getName(),new Subject(subjectEntity.getName(),new SchoolYear(subjectEntity.getName())), Arrays.asList( urls.split(",")));
    }


    public StudentEntity addStudent(Student student) {
        if (studentEntityRepository.findByFirebase_id(student.getFirebase_id()).isPresent()){
            return studentEntityRepository.findByFirebase_id(student.getFirebase_id()).get();
        }
        StudentEntity studentEntity=new StudentEntity();
        studentEntity.setName(student.getName());
        studentEntity.setFirebase_id(student.getFirebase_id());
        studentEntityRepository.save(studentEntity);
        return studentEntity;
    }

    public WatchedClassEntity addStudentClassWatched(Integer studentId, String classId) {
        Optional<WatchedClassEntity> isPresent=watchedClassEntityRepository.findById(new WatchedClassEntityKey(Integer.parseInt(classId),studentId));
        if (isPresent.isPresent()) return isPresent.get();
        StudentEntity studentEntity=studentEntityRepository.findById(studentId).get();
        ClassEntity classEntity=classEntityRepository.findById(Integer.parseInt(classId)).get();
        WatchedClassEntity watchedClassEntity=new WatchedClassEntity();
        watchedClassEntity.setId(new WatchedClassEntityKey(classEntity.getId(),studentEntity.getId()));
        watchedClassEntity.setClassEntity(classEntity);
        watchedClassEntity.setStudent(studentEntity);
        watchedClassEntity.setDateTime(java.time.LocalDateTime.now());
        watchedClassEntityRepository.save(watchedClassEntity);
        return watchedClassEntity;
    }

    public boolean isSameFirebaseUUID(String firebaseUUID, Integer studentId) {
        Optional<StudentEntity> studentEntity=studentEntityRepository.findById(studentId);
        return studentEntity.map(entity -> entity.getFirebase_id().equals(firebaseUUID)).orElse(false);

    }

    public CompletedTestEntity addStudentTestWatched(Integer studentId, String testId) {
        Optional<CompletedTestEntity> isPresent=completedTestEntityRepository.findById(new CompletedTestEntityKey(Integer.parseInt(testId),studentId));
        if (isPresent.isPresent()) return isPresent.get();
        StudentEntity studentEntity=studentEntityRepository.findById(studentId).get();
        TestEntity testEntity=testEntityRepository.findById(Integer.parseInt(testId)).get();
        CompletedTestEntity completedTestEntity=new CompletedTestEntity();
        completedTestEntity.setId(new CompletedTestEntityKey(testEntity.getId(),studentEntity.getId()));
        completedTestEntity.setTestEntity(testEntity);
        completedTestEntity.setStudent(studentEntity);
        completedTestEntity.setDateTime(java.time.LocalDateTime.now());
        completedTestEntityRepository.save(completedTestEntity);
        return completedTestEntity;
    }
}
