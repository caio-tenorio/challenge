package com.abletech.challenge.car;

import com.abletech.challenge.part.Part;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private Integer year;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "car_id")
    private List<Part> parts;

    @Transient
    private Double partsValue;
}
