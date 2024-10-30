package com.mathschool.MathSchoolBack.repositories;

import com.mathschool.MathSchoolBack.entities.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassEntityRepository extends JpaRepository<ClassEntity,Integer> {
}
