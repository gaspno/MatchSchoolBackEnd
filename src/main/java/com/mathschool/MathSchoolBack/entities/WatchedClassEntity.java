package com.mathschool.MathSchoolBack.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mathschool.MathSchoolBack.entities.key.WatchedClassEntityKey;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "aula_assistida")
public class WatchedClassEntity {

    @EmbeddedId
    private WatchedClassEntityKey id;

    @MapsId("student")
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "aluno_id")
    private StudentEntity student;
    @MapsId("classEntity")
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "aula_id")
    private ClassEntity classEntity;
    @Column(name = "data_concluida")
    private LocalDateTime dateTime;


}
