package com.mathschool.MathSchoolBack.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "assunto")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String name;
    @Column(name = "image_url")
    private String imageUrl;
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "ano_letivo_id")
    private SchoolYearEntity schoolYearEntity;
    @OneToMany(mappedBy = "subject")
    @JsonManagedReference
    private Set<TestEntity> testEntitySet;
    @OneToMany(mappedBy="subject")
    @JsonManagedReference
    private Set<ClassEntity> classEntitySet;

}
