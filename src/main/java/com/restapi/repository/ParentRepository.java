package com.restapi.repository;

import com.restapi.model.Address;
import com.restapi.model.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {
    @Query("select p from Parent p INNER JOIN p.appUser a WHERE a.id=?1")
    Parent findByUserId(Long id);
}
