package com.mathschool.MathSchoolBack.repositories;

import com.mathschool.MathSchoolBack.entities.CompletedTestEntity;
import com.mathschool.MathSchoolBack.entities.WatchedClassEntity;
import com.mathschool.MathSchoolBack.entities.key.CompletedTestEntityKey;
import com.mathschool.MathSchoolBack.entities.key.WatchedClassEntityKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompletedTestEntityRepository extends JpaRepository<CompletedTestEntity, CompletedTestEntityKey> {
    List<CompletedTestEntity> findByStudentId(Integer studentId);
}
