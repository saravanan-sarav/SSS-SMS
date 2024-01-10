package com.restapi.repository;

import com.restapi.model.Address;
import com.restapi.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    @Query("SELECT s FROM Subject s WHERE s.subject=?1")
    Subject findByName(String subjectName);

    @Query("SELECT s.subject, COUNT(t) FROM Subject s LEFT JOIN s.teacher t GROUP BY s.subject")
    List<Object[]> selectTeacherCountWithSubjects();
}
