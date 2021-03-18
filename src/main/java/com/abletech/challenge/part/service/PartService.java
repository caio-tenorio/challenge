package com.abletech.challenge.part.service;

import com.abletech.challenge.part.Part;
import com.abletech.challenge.part.PartQueryParams;
import com.abletech.challenge.part.dal.PartRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PartService {
    private final PartRepository partRepository;

    public Page<Part> findAll(PartQueryParams params) {
        BooleanBuilder query = params.getPredicate();
        Pageable pageable = params.getPageable();

        return partRepository.findAll(query, pageable);
    }
}
