package com.mathschool.MathSchoolBack.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "aluno")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nome")
    private String name;
    @Column(name = "firebase_id")
    private String firebase_id;
    @OneToMany(mappedBy = "student")
    @JsonBackReference
    private Set<WatchedClassEntity> classEntitySet;



}
