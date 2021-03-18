package com.abletech.challenge.part.dal;

import com.abletech.challenge.part.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PartRepository extends JpaRepository<Part, Long>, QuerydslPredicateExecutor<Part> {

}
