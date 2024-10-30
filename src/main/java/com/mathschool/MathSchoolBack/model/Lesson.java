package com.mathschool.MathSchoolBack.model;


import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Lesson {
    private String title;
    private Subject subject;
    private List<String> medias;
}
