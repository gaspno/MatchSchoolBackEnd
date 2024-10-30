package com.mathschool.MathSchoolBack.repositories;

import com.mathschool.MathSchoolBack.entities.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionEntityRepository extends JpaRepository<QuestionEntity,Integer> {
}
