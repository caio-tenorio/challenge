package com.abletech.challenge.car.dal;

import com.abletech.challenge.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CarRepository extends JpaRepository<Car, Long>, QuerydslPredicateExecutor<Car> {

}
