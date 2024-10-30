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
@Table(name = "aula")
public class ClassEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name ="nome" ,unique = true)
    private String name;
    @Column(name ="content_urls")
    private String content_urls;
    @ManyToOne
    @JoinColumn(name ="assunto_id" )
    @JsonBackReference
    private SubjectEntity subject;
    @OneToMany(mappedBy = "classEntity")
    @JsonBackReference
    private Set<WatchedClassEntity> classEntitySet;

}
