package com.ArtSeeReal.pro.oauthTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OAuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void kakaoAuthTest() throws Exception {
        String code = "XMo5H5RHlidWr1qcdv4_2m-FTJDLRbtL6LM50VZPuVpYjfKghJGd1wAAAAQKPXUaAAABj7M-OfvUNEQ5evY1pg";

        MvcResult result = mockMvc.perform(post("/oauth/kakao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(code))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println("Kakao Auth Response: " + content);
    }

    @Test
    void naverAuthTest() throws Exception {
        String code = "vn2XSjG2z1xZmTq4tx";

        MvcResult result = mockMvc.perform(post("/oauth/naver")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(code))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println("Naver Auth Response: " + content);
    }
}
