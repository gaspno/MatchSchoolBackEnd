package com.mathschool.MathSchoolBack.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.N;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class StudentRegister {
    private Student student;
    private List<Integer> lessonsId;
    private List<Integer> testsId;
}
