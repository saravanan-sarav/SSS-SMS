package com.restapi.repository;

import com.restapi.model.LeaveType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeaveTypeRepository extends JpaRepository<LeaveType, Long> {

    @Query("SELECT lt from LeaveType lt WHERE lt.leaveType=?1")
    Optional<LeaveType> findByLeaveType(String type);
}
