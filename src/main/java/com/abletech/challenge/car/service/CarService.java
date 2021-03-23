package com.abletech.challenge.car.service;

import com.abletech.challenge.car.Car;
import com.abletech.challenge.car.CarModelDamaged;
import com.abletech.challenge.car.CarQueryParams;
import com.abletech.challenge.car.dal.CarRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    public Page<Car> findAll(CarQueryParams params) {
        if (Objects.isNull(params)) {
            params = new CarQueryParams();
        }

        if (StringUtils.isBlank(params.getPreMadeFilter())) {
            return carRepository.findAll(params.getPredicate(), params.getPageable());
        }

        return getPreMadeFilterResponse(params);
    }

    private Page<Car> getPreMadeFilterResponse(CarQueryParams params) {
        validatePreMadeFilter(params);
        if (CarQueryParams.ORDER_BY_PARTS_VALUE.equals(params.getPreMadeFilter())) {
            return carRepository.findAllOrderByPartsValueSum(params.getPageable());
        }

        if (CarQueryParams.ORDER_BY_DAMAGED_PARTS_VALUE.equals(params.getPreMadeFilter())) {
            return carRepository.findAllByHasDamagedPartsOrderBySumDamagedPartsValue(params.getPageable());
        }

        return carRepository.findAll(params.getPredicate(), params.getPageable());
    }

    private void validatePreMadeFilter(CarQueryParams params) {
        Validate.notBlank(params.getPreMadeFilter(), "Pre made filter is null");
        Validate.isTrue(CarQueryParams.PRE_MADE_FILTERS.contains(params.getPreMadeFilter()), "Could not find pre made filter");
    }

    public void bulkInsert(List<Car> cars) {
        Validate.notEmpty(cars, "Cars can't be empty");
        carRepository.saveAll(cars);
    }

    public List<CarModelDamaged> getMostDamagedModels() {
        return carRepository.getMostDamagedModels();
    }
}
