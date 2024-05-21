package com.ArtSeeReal.pro.etcTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class TypesTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testReadRegions() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/regions"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()) // 응답 본문 출력
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code").value("I000"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].label").value("서울"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].code").value("H000"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].label").value("부산"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].code").value("F000"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].label").value("대구"));

        // 필요에 따라 더 많은 검증 추가
    }

    @Test
    void testReadCategories() throws Exception {
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
        mockMvc.perform(MockMvcRequestBuilders.get("/user/types"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code").value("ADMIN"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].label").value("운영자"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].code").value("AUTHOR"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].label").value("작가"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].code").value("PLANNER"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[2].label").value("기획자"));
        // Add more assertions as needed
    }
}