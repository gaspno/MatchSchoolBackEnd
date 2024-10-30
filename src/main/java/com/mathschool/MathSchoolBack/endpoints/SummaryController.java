package com.mathschool.MathSchoolBack.endpoints;


import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.database.annotations.NotNull;
import com.mathschool.MathSchoolBack.entities.StudentEntity;
import com.mathschool.MathSchoolBack.entities.SubjectEntity;
import com.mathschool.MathSchoolBack.model.*;
import com.mathschool.MathSchoolBack.services.FirebaseService;
import com.mathschool.MathSchoolBack.services.ServiceRetrieveData;
import com.mathschool.MathSchoolBack.services.ServiceStudentClassRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("summary")
public class SummaryController {

    @Autowired
    private ServiceRetrieveData serviceRetrieveData;
    @Autowired
    private ServiceStudentClassRegister serviceClassRegister;
    @Autowired
    private FirebaseService firebaseService;

    @GetMapping("years")
    public List<SchoolYear> years(){
        return serviceRetrieveData.getYears().stream().map(v->new SchoolYear(v.getName())).collect(Collectors.toList());
    }

    @GetMapping("{year}/subject")
    public List<SubjectEntity> getSubjects(@PathVariable(name = "year") String year){
        return serviceRetrieveData.getSubjectsByYear(year);
    }

    @GetMapping("class_list/{subject}")
    public List<Lesson> getClass( @PathVariable(name = "subject") String subjectName){
        return serviceRetrieveData.getClassBySubject(subjectName);
    }

    @GetMapping("test_list/{subject}")
    public List<Test> getT(@PathVariable(name = "subject") String subjectName){
        return serviceRetrieveData.getTestBySubject(subjectName);
    }
    @GetMapping("watched_list/{student_id}")
    public StudentRegister studentRegister(@PathVariable(name = "student_id") Integer studentId,@RequestHeader("Authorization") String tokenFirebase) throws FirebaseAuthException {
        FirebaseToken firebaseToken = firebaseService.getTokenValid(tokenFirebase);
        if (firebaseToken == null) {
            return null;
        }
        if (serviceClassRegister.isSameFirebaseUUID(firebaseToken.getUid(), studentId)) {
            return new StudentRegister(serviceRetrieveData.getStudent(studentId),
                    serviceRetrieveData.getWatchedClasses(studentId).stream().map(v -> v.getClassEntity().getId()).collect(Collectors.toList()),
                    serviceRetrieveData.getCompletedTests(studentId).stream().map(v -> v.getTestEntity().getId()).collect(Collectors.toList()));
        }else {
            return null;        }

    }


    @PostMapping("register_student_class_watched")
    @ResponseBody
    private ResponseEntity<StudentRegister> addStudentRegisterClass(@RequestParam("student_id") @NotNull Integer studentId, @RequestParam("class_id")@NotNull String classId, @RequestHeader("Authorization") String tokenFirebase) throws FirebaseAuthException {
        FirebaseToken firebaseToken = firebaseService.getTokenValid(tokenFirebase);
        if (firebaseToken == null) {
            return ResponseEntity.badRequest().build();
        }
        if (serviceClassRegister.isSameFirebaseUUID(firebaseToken.getUid(), studentId)) {
            serviceClassRegister.addStudentClassWatched(studentId,classId);
            StudentRegister studentRegister=new StudentRegister(serviceRetrieveData.getStudent(studentId),serviceRetrieveData.getWatchedClasses(studentId).stream().map(v->v.getClassEntity().getId()).collect(Collectors.toList()),null);
            return ResponseEntity.ok(studentRegister);
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("register_student_test_completed")
    @ResponseBody
    private ResponseEntity<StudentRegister> addStudentRegisterTest(@RequestParam("student_id") @NotNull Integer studentId, @RequestParam("test_id")@NotNull String testId, @RequestHeader("Authorization") String tokenFirebase) throws FirebaseAuthException {
        FirebaseToken firebaseToken = firebaseService.getTokenValid(tokenFirebase);
        if (firebaseToken == null) {
            return ResponseEntity.badRequest().build();
        }
        if (serviceClassRegister.isSameFirebaseUUID(firebaseToken.getUid(), studentId)) {
            serviceClassRegister.addStudentTestWatched(studentId,testId);
            StudentRegister studentRegister=new StudentRegister(serviceRetrieveData.getStudent(studentId),null,serviceRetrieveData.getCompletedTests(studentId).stream().map(v->v.getTestEntity().getId()).collect(Collectors.toList()));
            return ResponseEntity.ok(studentRegister);
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("student")
    @ResponseBody
    private ResponseEntity<Student> addStudent(@RequestBody Student student,@RequestHeader("Authorization") String tokenFirebase) throws FirebaseAuthException {
        FirebaseToken firebaseToken = firebaseService.getTokenValid(tokenFirebase);
        if (firebaseToken == null) {
            return ResponseEntity.badRequest().build();
        }
        if (firebaseToken.getUid().equals(student.getFirebase_id())) {
        StudentEntity studentEntity= serviceClassRegister.addStudent(student);
        return ResponseEntity.ok(new Student(studentEntity.getId(),studentEntity.getName(),studentEntity.getFirebase_id()));
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
}
