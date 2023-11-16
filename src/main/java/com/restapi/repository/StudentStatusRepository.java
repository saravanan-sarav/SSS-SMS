package com.restapi.repository;

import com.restapi.model.Address;
import com.restapi.model.StudentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentStatusRepository extends JpaRepository<StudentStatus, Long> {
    @Query("SELECT s FROM StudentStatus s WHERE s.status=?1")
    StudentStatus findByName(String pending);
}
