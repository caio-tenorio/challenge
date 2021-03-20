package com.abletech.challenge.part.dal;

import com.abletech.challenge.part.Part;
import com.abletech.challenge.part.PartDamaged;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface PartRepository extends JpaRepository<Part, Long>, QuerydslPredicateExecutor<Part> {
    @Query( value = "SELECT new com.abletech.challenge.part.PartDamaged(p.name, count(p.id)) " +
            "FROM Part p WHERE p.damaged = true " +
            "GROUP BY p.name " +
            "ORDER BY count(p.id) DESC")
    List<PartDamaged> findMostDamagedParts();
}
