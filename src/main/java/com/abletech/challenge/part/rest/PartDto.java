package com.abletech.challenge.part.rest;

import com.abletech.challenge.part.Part;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PartDto {
    private Long id;
    private String name;
    private Long value;
    private Boolean damaged;

    public static PartDto of (Part entity) {
        if (entity == null) {
            return null;
        }

        return PartDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .value(entity.getValue())
                .damaged(entity.getDamaged())
                .build();
    }

    public Part to () {
        return Part.builder()
                .name(name)
                .value(value)
                .damaged(damaged)
                .build();
    }
}
