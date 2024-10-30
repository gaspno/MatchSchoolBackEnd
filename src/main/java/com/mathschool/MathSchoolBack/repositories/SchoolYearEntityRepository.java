package com.mathschool.MathSchoolBack.repositories;

import com.mathschool.MathSchoolBack.entities.SchoolYearEntity;
import com.mathschool.MathSchoolBack.model.SchoolYear;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SchoolYearEntityRepository extends JpaRepository<SchoolYearEntity,Integer> {
    Optional<SchoolYearEntity> findByName(String yearName);
}
