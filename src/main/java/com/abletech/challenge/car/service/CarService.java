package com.abletech.challenge.car.service;

import com.abletech.challenge.car.Car;
import com.abletech.challenge.car.CarQueryParams;
import com.abletech.challenge.car.dal.CarRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    public Page<Car> findAll(CarQueryParams params) {
        BooleanBuilder predicate = params.getPredicate();
        Pageable pageable = params.getPageable();
        return carRepository.findAll(predicate, pageable);
    }

    public void bulkInsert(List<Car> cars) {
        carRepository.saveAll(cars);
    }
}
