package com.abletech.challenge.part;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartDamaged {
    private String partName;
    private Long damagedParts;
}
