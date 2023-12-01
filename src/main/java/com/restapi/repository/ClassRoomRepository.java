package com.restapi.repository;

import com.restapi.model.Address;
import com.restapi.model.ClassRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassRoomRepository extends JpaRepository<ClassRoom, Long> {
    @Query("SELECT c FROM ClassRoom c INNER JOIN c.classStandard cs WHERE cs.id=?1")
    Optional<ClassRoom> findByClassStandard(Long id);

    @Query("SELECT c FROM ClassRoom c INNER JOIN c.teacherUserClassRoom ct WHERE ct.id=?1")
    Optional<ClassRoom> findByTeacherUserClassRoom(Long id);

    @Query("SELECT c FROM ClassRoom c INNER JOIN c.classStandard ct WHERE ct.id=?1")
    Optional<ClassRoom> findByStandardId(Long standardId);
}
