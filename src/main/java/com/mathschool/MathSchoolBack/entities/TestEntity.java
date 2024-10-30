package com.mathschool.MathSchoolBack.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mathschool.MathSchoolBack.model.Subject;
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
@Table(name = "teste")
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "assunto_id")
    private SubjectEntity subject;
    @JsonManagedReference
    @OneToMany(mappedBy = "testEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<QuestionEntity> questionEntitySet;
}
