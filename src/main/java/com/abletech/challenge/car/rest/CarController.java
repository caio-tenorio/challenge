package com.abletech.challenge.car.rest;

import com.abletech.challenge.car.Car;
import com.abletech.challenge.car.CarModelDamaged;
import com.abletech.challenge.car.CarQueryParams;
import com.abletech.challenge.car.service.CarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/v1/cars")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping
    public Page<CarDto> pagedCars(CarQueryParams params) {
        log.info("Getting list of cars");
        Page<Car> cars = carService.findAll(params);
        return cars.map(CarDto::of);
    }

    @GetMapping("/model/most-damaged")
    public List<CarModelDamaged> getMostDamagedModels() {
        log.info("Getting most damaged models");
        return carService.getMostDamagedModels();
    }

    @PostMapping
    public void bulkInsert(@RequestBody List<CarDto> cars) {
        log.info("Inserting cars list");
        Validate.isTrue(CollectionUtils.isNotEmpty(cars), "Cars can't be empty");
        carService.bulkInsert(cars.stream().map(CarDto::to).collect(Collectors.toList()));
    }
}
