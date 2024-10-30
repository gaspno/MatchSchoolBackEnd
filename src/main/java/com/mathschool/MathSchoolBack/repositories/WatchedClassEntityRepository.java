package com.mathschool.MathSchoolBack.repositories;

import com.mathschool.MathSchoolBack.entities.WatchedClassEntity;
import com.mathschool.MathSchoolBack.entities.key.WatchedClassEntityKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WatchedClassEntityRepository extends JpaRepository<WatchedClassEntity, WatchedClassEntityKey> {
    List<WatchedClassEntity> findByStudentId(Integer studentId);
}
