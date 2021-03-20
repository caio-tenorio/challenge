package com.abletech.challenge.car.dal;

import com.abletech.challenge.car.Car;
import com.abletech.challenge.car.CarModelDamaged;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long>, QuerydslPredicateExecutor<Car> {

    @Query(value = "SELECT * FROM car c ORDER BY (SELECT SUM (value) FROM part p WHERE p.car_id = c.id) DESC", nativeQuery = true)
    Page<Car> findAllOrderByPartsValueSum(Pageable pageable);

    @Query(value = "select new com.abletech.challenge.car.CarModelDamaged(c.model, count(c.id)) " +
            "FROM Car c, Part p " +
            "WHERE p.car.id = c.id " +
            "AND p.damaged = true " +
            "GROUP BY c.model " +
            "ORDER BY count(c.id) DESC")
    List<CarModelDamaged> getMostDamagedModels();
}
