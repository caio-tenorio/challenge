package com.abletech.challenge.car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarModelDamaged {
    private String model;
    private Long damagedParts;
}
