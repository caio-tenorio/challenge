package com.abletech.challenge.car.rest;

import com.abletech.challenge.car.Car;
import com.abletech.challenge.part.rest.PartDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToMany;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    private Long id;
    private String brand;
    private String model;
    private Integer year;
    private List<PartDto> parts;

    public static CarDto of (Car entity) {
        if (entity == null) {
            return null;
        }

        return CarDto.builder()
                .id(entity.getId())
                .brand(entity.getBrand())
                .model(entity.getModel())
                .year(entity.getYear())
                .parts(entity.getParts() != null ? entity.getParts().stream().map(PartDto::of).collect(Collectors.toList()) : null )
                .build();
    }

    public Car to () {
        return Car.builder()
                .brand(brand)
                .model(model)
                .year(year)
                .parts(parts.stream().map(PartDto::to).collect(Collectors.toList()))
                .build();
    }
}
