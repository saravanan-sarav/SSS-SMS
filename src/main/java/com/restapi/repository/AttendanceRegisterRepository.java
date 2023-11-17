package com.restapi.repository;

import com.restapi.model.Address;
import com.restapi.model.AttendanceRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRegisterRepository extends JpaRepository<AttendanceRegister, Long> {
    @Query("SELECT att FROM AttendanceRegister att INNER JOIN att.studentUserAttendance satt WHERE satt.id=?1")
    Optional<List<AttendanceRegister>> findAllByUserId(Long id);

    @Query("SELECT att FROM AttendanceRegister att INNER JOIN att.classRoom c WHERE c.id=?1")
    List<AttendanceRegister> findAllByClassId(Long id);
}
