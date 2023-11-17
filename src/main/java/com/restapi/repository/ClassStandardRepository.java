package com.restapi.repository;

import com.restapi.model.AssignmentGrade;
import com.restapi.model.ClassStandard;
import org.assertj.core.internal.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassStandardRepository extends JpaRepository<ClassStandard, Long> {

    ClassStandard findByStandard(String standard);
}
