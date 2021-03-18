package com.abletech.challenge.part;

import com.abletech.challenge.paging.QueryParams;
import com.querydsl.core.BooleanBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.data.domain.Sort.Direction.DESC;

public class PartQueryParams extends QueryParams {
    private static final String NAME = "name";
    private static final String VALUE = "value";
    private static final String DAMAGED = "damaged";

    private String name;
    private List<Long> value; //TODO: make this a range
    private Boolean damaged;

    @Override
    public BooleanBuilder getPredicate() {
        QPart qPart = QPart.part;

        BooleanBuilder query = new BooleanBuilder();

        if (StringUtils.isNotBlank(name)) {
            query.and(qPart.name.trim().toLowerCase().contains(name.trim().toLowerCase()));
        }

        if (damaged != null) {
            query.and(qPart.damaged.eq(damaged));
        }

        return query;
    }

    @Override
    public Sort getSorting() {
        QPart qPart = QPart.part;

        Sort defaultSort = Sort.by(qPart.id.getMetadata().getName());

        if (getSort() == null) {
            return defaultSort;
        }

        String field = getSort().split(",")[0];
        Sort.Direction direction = getSort().endsWith("desc") ? DESC : ASC;

        switch (field) {
            case NAME:
                return Sort.by(direction, qPart.name.getMetadata().getName());
            case VALUE:
                return Sort.by(direction, qPart.value.getMetadata().getName());
            case DAMAGED:
                return Sort.by(direction, qPart.damaged.getMetadata().getName());
            default:
                return defaultSort;
        }
    }
}
