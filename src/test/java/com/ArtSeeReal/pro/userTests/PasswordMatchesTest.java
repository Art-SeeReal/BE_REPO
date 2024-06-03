package com.ArtSeeReal.pro.userTests;

import com.ArtSeeReal.pro.dto.user.UserCreateRequestDTO;
import com.ArtSeeReal.pro.enums.UserType;
import com.ArtSeeReal.pro.service.TokenService;
import com.ArtSeeReal.pro.service.UserService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@SpringJUnitWebConfig
public class PasswordMatchesTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private final UserService userService;
    private final TokenService tokenService;

    @Autowired
    public PasswordMatchesTest(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        createDummyData();
    }

    void createDummyData(){
        UserCreateRequestDTO createDTO = UserCreateRequestDTO.builder()
                .userId("recruitmentWriter")
                .name("읽는 사람")
                .password("test1234")
                .nickname("읽는 사람 닉네임")
                .email("portfolio_writer@gmail.com")
                .emailSecret(true)
                .phone("010-1234-5678")
                .phoneSecret(true)
                .userType(UserType.AUTHOR)
                .regDate(LocalDateTime.now())
                .build();
        String readUserUid = userService.createUser(createDTO).getUid();
    }

    @Test
    void 비밀번호_매치() throws Exception {
        String aToken = tokenService.createToken("access", "recruitmentWriter", "AUTHOR", 600000L);
        MvcResult result = mockMvc.perform(post("/user/auth")
                        .content("test1234")
                        .header("Authorization", aToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        // Optionally, print the response for debugging
        String content = result.getResponse().getContentAsString();
        System.out.println("Response: " + content);
    }
}
