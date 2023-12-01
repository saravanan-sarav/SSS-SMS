package com.restapi.repository;

import com.restapi.model.Address;
import com.restapi.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    @Query("SELECT a FROM Assignment a INNER JOIN a.teacherUserAssignment t WHERE t.id=?1")
    Optional<List<Assignment>> findByTeacherUserAssignment(Long id);

    @Query("SELECT a FROM Assignment a INNER JOIN a.classRoom c WHERE c.id=?1")
    Optional<List<Assignment>> findByClassRoomId(Long id);
}
