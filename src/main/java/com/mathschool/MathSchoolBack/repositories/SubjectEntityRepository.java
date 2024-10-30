package com.mathschool.MathSchoolBack.repositories;


import com.mathschool.MathSchoolBack.entities.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectEntityRepository extends JpaRepository<SubjectEntity,Integer> {
    Optional<SubjectEntity> findByName(String subjectName);

    List<SubjectEntity> findBySchoolYearEntityName(String year);
}
