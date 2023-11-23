package com.restapi.repository;

import com.restapi.model.Address;
import com.restapi.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {
    @Query("select p from Parent p INNER JOIN p.parentUser a WHERE a.id=?1")
    Optional<Parent> findByUserId(Long id);

    @Query("select p from Parent p INNER JOIN p.studentUserForParent a WHERE a.id=?1")
    Parent findByStudentUserId(Long id);
}
