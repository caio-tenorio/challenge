package com.abletech.challenge.part.rest;

import com.abletech.challenge.part.Part;
import com.abletech.challenge.part.PartQueryParams;
import com.abletech.challenge.part.service.PartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/parts")
@RequiredArgsConstructor
public class PartController {
    private final PartService partService;

    @GetMapping
    public Page<PartDto> listParts(PartQueryParams params) {
        log.info("Getting list of cars");
        Page<Part> parts = partService.findAll(params);
        return parts.map(PartDto::of);
    }
}
