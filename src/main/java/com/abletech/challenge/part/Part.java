package com.abletech.challenge.part;

import com.abletech.challenge.car.Car;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Long value;
    private Boolean damaged;

    @ManyToOne(fetch = FetchType.LAZY)
    private Car car;
}
