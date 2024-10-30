package com.mathschool.MathSchoolBack.repositories;

import com.mathschool.MathSchoolBack.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StudentEntityRepository extends JpaRepository<StudentEntity,Integer> {

    @Query("SELECT s FROM StudentEntity s WHERE s.firebase_id = ?1")
    Optional<StudentEntity> findByFirebase_id(String firebaseId);
}
