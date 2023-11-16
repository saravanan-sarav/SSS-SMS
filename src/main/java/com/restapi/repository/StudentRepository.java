package com.restapi.repository;

import com.restapi.model.Address;
import com.restapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s WHERE INNER JOIN s.appUser a WHERE a.id=?1")
    Student findByUserId(Long id);
}
