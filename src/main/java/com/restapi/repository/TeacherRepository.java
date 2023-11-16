package com.restapi.repository;

import com.restapi.model.Address;
import com.restapi.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    @Query("SELECT t FROM Teacher t INNER JOIN t.appUser a WHERE a.id=?1")
    Teacher findByUserId(Long id);
}
