package com.restapi.repository;

import com.restapi.model.Address;
import com.restapi.model.AssignmentGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentGradeRepository extends JpaRepository<AssignmentGrade, Long> {

}
