package com.mathschool.MathSchoolBack.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class Test {
    private Subject subject;
    private List<Question> questions;

}
