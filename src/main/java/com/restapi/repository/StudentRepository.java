package com.restapi.repository;

import com.restapi.model.Address;
import com.restapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s INNER JOIN s.studentUser a WHERE a.id=?1")
    Optional<Student> findByUserId(Long id);
}
