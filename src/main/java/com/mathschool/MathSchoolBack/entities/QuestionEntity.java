package com.mathschool.MathSchoolBack.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "questao")
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id" )
    private Integer id;
    @Column(name ="alternativa1" )
    private String alternativa1;
    @Column(name ="alternativa2" )
    private String alternativa2;
    @Column(name ="alternativa3" )
    private String alternativa3;
    @Column(name ="alternativa4" )
    private String alternativa4;
    @Column(name ="enunciado" )
    private String enunciado;
    @Column(name ="correct_anwser" )
    private int indexRespostaCorreta;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "teste_id")
    private TestEntity testEntity;


}
