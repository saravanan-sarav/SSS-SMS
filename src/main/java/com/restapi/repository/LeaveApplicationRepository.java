package com.restapi.repository;

import com.restapi.model.LeaveApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LeaveApplicationRepository extends JpaRepository<LeaveApplication, Long> {
    @Query("SELECT leave FROM LeaveApplication leave INNER JOIN leave.leaveApplicationStudent leaveStudent WHERE leaveStudent.id=?1")
    Optional<List<LeaveApplication>> findByStudentId(Long studentUserId);
}
