package com.restapi.repository;

import com.restapi.model.Address;
import com.restapi.model.AssignmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentTypeRepository extends JpaRepository<AssignmentType, Long> {
    @Query("SELECT t FROM AssignmentType t WHERE t.type=?1")
    AssignmentType findByName(String type);
}
