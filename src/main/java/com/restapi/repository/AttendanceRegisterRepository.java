package com.restapi.repository;

import com.restapi.model.Address;
import com.restapi.model.AttendanceRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRegisterRepository extends JpaRepository<AttendanceRegister, Long> {

}
