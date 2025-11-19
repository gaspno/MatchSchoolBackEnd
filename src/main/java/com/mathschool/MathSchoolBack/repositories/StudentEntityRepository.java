package com.mathschool.MathSchoolBack.repositories;

import com.mathschool.MathSchoolBack.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StudentEntityRepository extends JpaRepository<StudentEntity,Integer> {

    /*
     * Spring Data JPA can derive the query from the method name.
     * This is more readable and less error-prone than using the @Query annotation.
     * The method below can be replaced with:
     * Optional<StudentEntity> findByFirebaseId(String firebaseId);
     */
    @Query("SELECT s FROM StudentEntity s WHERE s.firebase_id = ?1")
    Optional<StudentEntity> findByFirebase_id(String firebaseId);
}
