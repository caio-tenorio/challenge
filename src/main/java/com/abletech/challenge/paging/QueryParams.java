package com.abletech.challenge.paging;

import com.querydsl.core.BooleanBuilder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;

@Data
public abstract class QueryParams {
    public static final int DEFAULT_PAGE_NUMBER = 0;
    public static final int DEFAULT_PAGE_SIZE = 50;

    private String sort;
    private int page = DEFAULT_PAGE_NUMBER;
    private int size = DEFAULT_PAGE_SIZE;

    public abstract Sort getSorting();
    public abstract BooleanBuilder getPredicate();

    public Pageable getPageable() {
        Sort sorting = getSorting();
        if (Objects.isNull(sorting)) {
            return PageRequest.of(getPage(), getSize());
        }
        return PageRequest.of(getPage(), getSize(), sorting);
    }
}