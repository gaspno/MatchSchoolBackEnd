package com.mathschool.MathSchoolBack.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mathschool.MathSchoolBack.entities.key.CompletedTestEntityKey;
import com.mathschool.MathSchoolBack.entities.key.WatchedClassEntityKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "teste_concluido")
public class CompletedTestEntity {

    @EmbeddedId
    private CompletedTestEntityKey id;

    @MapsId("student")
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "aluno_id")
    private StudentEntity student;
    @MapsId("testEntity")
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "teste_id")
    private TestEntity testEntity;
    @Column(name = "data_concluida")
    private LocalDateTime dateTime;


}
