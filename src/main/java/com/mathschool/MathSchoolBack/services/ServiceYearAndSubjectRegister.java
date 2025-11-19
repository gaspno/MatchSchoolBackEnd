package com.mathschool.MathSchoolBack.services;

import com.mathschool.MathSchoolBack.entities.QuestionEntity;
import com.mathschool.MathSchoolBack.entities.SchoolYearEntity;
import com.mathschool.MathSchoolBack.entities.SubjectEntity;
import com.mathschool.MathSchoolBack.entities.TestEntity;
import com.mathschool.MathSchoolBack.model.Question;
import com.mathschool.MathSchoolBack.model.SchoolYear;
import com.mathschool.MathSchoolBack.model.Subject;
import com.mathschool.MathSchoolBack.model.Test;
import com.mathschool.MathSchoolBack.repositories.QuestionEntityRepository;
import com.mathschool.MathSchoolBack.repositories.SchoolYearEntityRepository;
import com.mathschool.MathSchoolBack.repositories.SubjectEntityRepository;
import com.mathschool.MathSchoolBack.repositories.TestEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/*
 * This class is a service that contains business logic.
 * It is not a controller, so the @RestController annotation is not appropriate.
 * It should be replaced with the @Service annotation.
 */
@RestController
@RequestMapping("")
public class ServiceYearAndSubjectRegister {

    @Autowired
    private SchoolYearEntityRepository schoolYearEntityRepository;
    @Autowired
    private SubjectEntityRepository subjectEntityRepository;
    @Autowired
    private TestEntityRepository testEntityRepository;
    @Autowired
    private QuestionEntityRepository questionEntityRepository;

    public SchoolYear addYear(String  name){
        SchoolYearEntity schoolYearEntity=new SchoolYearEntity();
        schoolYearEntity.setName(name);
        return new SchoolYear(schoolYearEntityRepository.save(schoolYearEntity).getName());

    }

    public Subject addSubject(String yearName, String subjectName,String imageURL) {
        Optional<SchoolYearEntity> schoolYearEntity= schoolYearEntityRepository.findByName(yearName);
        if (schoolYearEntity.isPresent()){
            System.out.println(schoolYearEntity.get().getId());
            SubjectEntity subjectEntity=new SubjectEntity();
            subjectEntity.setName(subjectName);
            subjectEntity.setImageUrl(imageURL);
            subjectEntity.setSchoolYearEntity(schoolYearEntity.get());
            subjectEntity.setTestEntitySet(null);
            subjectEntity.setClassEntitySet(null);
            subjectEntity=subjectEntityRepository.save(subjectEntity);
            return new Subject(subjectEntity.getName(),new SchoolYear(subjectEntity.getSchoolYearEntity().getName()));
        }
        return null;
    }

    public Test addTest(String subjectName, List<Question> questions) {
        Optional<SubjectEntity> subjectEntityOptional=subjectEntityRepository.findByName(subjectName);
        SubjectEntity subjectEntity=subjectEntityOptional.get();
        TestEntity testEntity=new TestEntity();
        testEntity.setQuestionEntitySet
                (questions.stream().map(q->new QuestionEntity(null,q.getOptions()[0],q.getOptions()[1],q.getOptions()[2],q.getOptions()[3],q.getQuestion(),q.getIndexCorrectAnswer(),testEntity)).collect(Collectors.toSet()));
        testEntity.setSubject(subjectEntity);
        testEntityRepository.save(testEntity);
        return new Test(new Subject(subjectEntity.getName(),new SchoolYear(subjectEntity.getName())),(questions));

    }


}
