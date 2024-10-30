package com.mathschool.MathSchoolBack.entities.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@EqualsAndHashCode
public class CompletedTestEntityKey implements Serializable {

    @Column(name = "teste_id")
    private Integer testEntity;
    @Column(name = "aluno_id")
    private Integer student;


}
