package com.abletech.challenge;

import com.abletech.challenge.part.Part;
import com.abletech.challenge.part.PartDamaged;
import com.abletech.challenge.part.PartQueryParams;
import com.abletech.challenge.part.dal.PartRepository;
import com.abletech.challenge.part.service.PartService;
import com.querydsl.core.BooleanBuilder;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PartTests {
    @Autowired
    private MockMvc mockMvc;

    private final PartRepository partRepository = Mockito.mock(PartRepository.class);

    @Test
    public void getMostDamaged() throws Exception {
        PartService partService =  new PartService(partRepository);
        List<PartDamaged> expectedResponse = List.of(new PartDamaged("Farol", 30L), new PartDamaged("Retrovisor", 15L));

        mockMvc.perform(get("/api/v1/parts/most-damaged"))
                .andExpect(status().isOk());

        Mockito.when(partRepository.findMostDamagedParts()).thenReturn(expectedResponse);

        List<PartDamaged> response = partService.getMostDamagedParts();

        assertThat(response).isNotEmpty();
        assertThat(response.size()).isEqualTo(expectedResponse.size());
        assertThat(response.get(0).getPartName()).isEqualTo(expectedResponse.get(0).getPartName());
        assertThat(response.get(1).getPartName()).isEqualTo(expectedResponse.get(1).getPartName());
    }

    @Test
    public void findAll() throws Exception {
        PartService partService =  new PartService(partRepository);
        Page<Part> expectedResponse = new PageImpl<>(List.of(new Part(1L, "Farol", 2100L, true), new Part(2L, "Retrovisor", 1250L, false)));

        mockMvc.perform(get("/api/v1/parts"))
                .andExpect(status().isOk());

        PartQueryParams partQueryParams = new PartQueryParams();
        BooleanBuilder query = partQueryParams.getPredicate();
        Pageable pageable = partQueryParams.getPageable();

        Mockito.when(partRepository.findAll(query, pageable)).thenReturn(expectedResponse);

        Page<Part> response = partService.findAll(partQueryParams);

        assertThat(response).isNotNull();
        assertThat(response.getTotalElements()).isEqualTo(expectedResponse.getTotalElements());
        assertThat(response.getContent().get(0).getId()).isEqualTo(expectedResponse.getContent().get(0).getId());
    }
}
