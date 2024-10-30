package com.mathschool.MathSchoolBack.model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Question {
    private String question;
    private String[]options;
    private int indexCorrectAnswer;
}
