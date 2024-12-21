package com.estuate.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.estuate.model.Resource;


@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {
    @Query("SELECT DISTINCT r FROM Resource r JOIN r.skills s WHERE s IN :skills GROUP BY r HAVING COUNT(DISTINCT s) = :skillCount")
    List<Resource> findBySkillsAll(@Param("skills") Set<String> skills, @Param("skillCount") long skillCount);
    
    @Query("SELECT DISTINCT r FROM Resource r JOIN r.skills s WHERE s IN :skills AND r.experience < :maxExperience GROUP BY r HAVING COUNT(DISTINCT s) = :skillCount")
    List<Resource> findBySkillsAndExperience(@Param("skills") Set<String> skills, @Param("skillCount") long skillCount, @Param("maxExperience") int maxExperience);
}
