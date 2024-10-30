package com.mathschool.MathSchoolBack.entities.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@EqualsAndHashCode
public class WatchedClassEntityKey implements Serializable {

    @Column(name = "aula_id")
    private Integer classEntity;
    @Column(name = "aluno_id")
    private Integer student;


}
