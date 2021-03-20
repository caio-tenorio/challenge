package com.abletech.challenge;

import com.abletech.challenge.car.Car;
import com.abletech.challenge.car.CarModelDamaged;
import com.abletech.challenge.car.CarQueryParams;
import com.abletech.challenge.car.dal.CarRepository;
import com.abletech.challenge.car.service.CarService;
import com.querydsl.core.BooleanBuilder;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class CarTests {
    @Autowired
    private MockMvc mockMvc;

    private final CarRepository carRepository = Mockito.mock(CarRepository.class);

    @Test
    public void getDamagedModels() throws Exception {
        CarService carService =  new CarService(carRepository);
        List<CarModelDamaged> expectedResponse = List.of(new CarModelDamaged("4x4", 30L), new CarModelDamaged("Conversível", 15L));

        mockMvc.perform(get("/api/v1/cars/model/most-damaged"))
                .andExpect(status().isOk());

        Mockito.when(carRepository.getMostDamagedModels()).thenReturn(expectedResponse);

        List<CarModelDamaged> response = carService.getMostDamagedModels();

        assertThat(response).isNotEmpty();
        assertThat(response.size()).isEqualTo(expectedResponse.size());
        assertThat(response.get(0).getModel()).isEqualTo(expectedResponse.get(0).getModel());
        assertThat(response.get(1).getModel()).isEqualTo(expectedResponse.get(1).getModel());
    }

    @Test
    public void findAll() throws Exception {
        CarService carService =  new CarService(carRepository);
        Page<Car> expectedResponse = new PageImpl<>(List.of(new Car(1L, "Jeep", "4x4", 2019), new Car(2L, "Fiat", "Conversível", 2018)));

        mockMvc.perform(get("/api/v1/cars"))
                .andExpect(status().isOk());

        CarQueryParams carQueryParams = new CarQueryParams();
        BooleanBuilder query = carQueryParams.getPredicate();
        Pageable pageable = carQueryParams.getPageable();
;
        Mockito.when(carRepository.findAll(query, pageable)).thenReturn(expectedResponse);

        Page<Car> response = carService.findAll(carQueryParams);

        assertThat(response).isNotNull();
        assertThat(response.getTotalElements()).isEqualTo(expectedResponse.getTotalElements());
        assertThat(response.getContent().get(0).getId()).isEqualTo(expectedResponse.getContent().get(0).getId());
    }
}