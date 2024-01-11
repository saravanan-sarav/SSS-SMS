package com.restapi.repository;

import com.restapi.model.LeaveReason;
import com.restapi.model.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeaveReasonRepository extends JpaRepository<LeaveReason,Long> {

    @Query("SELECT lr from LeaveReason lr WHERE lr.reason=?1")
    Optional<LeaveReason> findByReason(String reason);
}
