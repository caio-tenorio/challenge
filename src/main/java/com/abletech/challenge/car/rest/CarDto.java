package com.abletech.challenge.car.rest;

import com.abletech.challenge.car.Car;
import com.abletech.challenge.part.Part;
import com.abletech.challenge.part.rest.PartDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

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
    private Long partsValue;
    private Long damagedPartsValue;

    public static CarDto of (Car entity) {
        if (entity == null) {
            return null;
        }

        return CarDto.builder()
                .id(entity.getId())
                .brand(entity.getBrand())
                .model(entity.getModel())
                .year(entity.getYear())
                .parts(entity.getParts() != null ? entity.getParts().stream().map(PartDto::of).collect(Collectors.toList()) : null)
                .partsValue(getTotalPartsValue(entity.getParts()))
                .damagedPartsValue(getTotalDamagedPartsValue(entity.getParts()))
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

    private static Long getTotalPartsValue(List<Part> parts) {
        if (CollectionUtils.isEmpty(parts)) {
            return null;
        }

        List<Long> partValues = parts.stream().map(Part::getValue).collect(Collectors.toList());
        return partValues.stream().reduce(0L, Long::sum);
    }

    private static Long getTotalDamagedPartsValue(List<Part> parts) {
        if (CollectionUtils.isEmpty(parts)) {
            return null;
        }

        List<Long> partValues = parts.stream().map(el -> el.getDamaged() ? el.getValue() : 0L).collect(Collectors.toList());
        return partValues.stream().reduce(0L, Long::sum);
    }
}
