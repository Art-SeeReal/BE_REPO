package com.ArtSeeReal.pro.etcTests;

import com.ArtSeeReal.pro.controller.EtcController;
import com.ArtSeeReal.pro.enums.CategoryType;
import com.ArtSeeReal.pro.enums.RegionType;
import com.ArtSeeReal.pro.enums.UserType;
import com.ArtSeeReal.pro.service.EtcService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.mockito.Mockito.when;

@WebMvcTest(EtcController.class)
public class TypesTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EtcService etcService;

    @Test
    void testReadRegions() throws Exception {
        when(etcService.regionCodeRead()).thenReturn(Arrays.asList(RegionType.values()));
        mockMvc.perform(MockMvcRequestBuilders.get("/regions"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code").value("I000"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].label").value("서울"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].code").value("H000"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].label").value("부산"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].code").value("F000"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].label").value("대구"));
        // Add more assertions as needed
    }

    @Test
    void testReadCategories() throws Exception {
        when(etcService.categoryCodeRead()).thenReturn(Arrays.asList(CategoryType.values()));
        mockMvc.perform(MockMvcRequestBuilders.get("/fields"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code").value("A000"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].label").value("미술"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].code").value("B000"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].label").value("공예"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].code").value("C000"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].label").value("디자인"));
        // Add more assertions as needed
    }

    @Test
    void testReadUserTypes() throws Exception {
        when(etcService.userCodeRead()).thenReturn(Arrays.asList(UserType.values()));
        mockMvc.perform(MockMvcRequestBuilders.get("/user/types"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("admin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].korean").value("운영자"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].number").value(0))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("author"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].korean").value("작가"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].number").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].name").value("planner"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].korean").value("기획자"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].number").value(2));
        // Add more assertions as needed
    }
}
