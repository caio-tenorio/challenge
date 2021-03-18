package com.abletech.challenge.car;

import com.abletech.challenge.paging.QueryParams;
import com.querydsl.core.BooleanBuilder;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

public class CarQueryParams extends QueryParams {
    private static final String BRAND = "brand";
    private static final String MODEL = "model";
    private static final String YEAR = "year";

    private List<String> brands;
    private List<String> models;
    private List<Integer> years; //TODO: make this a range

    @Override
    public BooleanBuilder getPredicate() {
        QCar qCar = QCar.car;
        BooleanBuilder query = new BooleanBuilder();

        if (CollectionUtils.isNotEmpty(brands)) {
            query.and(qCar.brand.in(brands));
        }

        if (CollectionUtils.isNotEmpty(models)) {
            query.and(qCar.model.in(models));
        }

        if (CollectionUtils.isNotEmpty(years)) {
            query.and(qCar.year.in(years));
        }

        return query;
    }

    @Override
    public Sort getSorting() {
        QCar qCar = QCar.car;

        Sort defaultSort = Sort.by(qCar.id.getMetadata().getName());

        if (getSort() == null) {
            return defaultSort;
        }

        String field = getSort().split(",")[0];
        Sort.Direction direction = getSort().endsWith("desc") ? DESC : ASC;

        switch (field) {
            case BRAND:
                return Sort.by(direction, qCar.brand.getMetadata().getName());
            case MODEL:
                return Sort.by(direction, qCar.model.getMetadata().getName());
            case YEAR:
                return Sort.by(direction, qCar.year.getMetadata().getName());
            default:
                return defaultSort;
        }
    }
}
