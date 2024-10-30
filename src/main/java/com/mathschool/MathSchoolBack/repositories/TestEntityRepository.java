package com.mathschool.MathSchoolBack.repositories;


import com.mathschool.MathSchoolBack.entities.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestEntityRepository extends JpaRepository<TestEntity,Integer> {
}
