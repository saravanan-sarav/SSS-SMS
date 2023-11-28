package com.restapi.repository;

import com.restapi.model.Address;
import com.restapi.model.AssignmentGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssignmentGradeRepository extends JpaRepository<AssignmentGrade, Long> {

    @Query("SELECT a FROM AssignmentGrade a INNER JOIN a.studentUserAssignmentGrade astu INNER JOIN a.assignmentForGrade aa WHERE aa.id=?1 AND astu.id=?2")
    Optional<AssignmentGrade> findByStudentUserAssignmentGradeAndAssignment(Long assignmentId, Long studentUserId);
    @Query("SELECT a FROM AssignmentGrade a INNER JOIN a.studentUserAssignmentGrade astu WHERE astu.id=?1")
    Optional<AssignmentGrade> findByStudentUserAssignment(Long studentUserId);
}
