package com.restapi.repository;

import com.restapi.model.LeaveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeaveStatusRepository extends JpaRepository<LeaveStatus,Long> {
    @Query("SELECT ls FROM LeaveStatus ls WHERE ls.leaveStatus=?1")
    Optional<LeaveStatus> findByLeaveStatus(String status);
}
