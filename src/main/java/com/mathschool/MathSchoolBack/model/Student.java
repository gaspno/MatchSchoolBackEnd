package com.mathschool.MathSchoolBack.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Student {
    private Integer id;
    private String name;
    @JsonProperty("firebase_id")
    private String firebase_id;
}
