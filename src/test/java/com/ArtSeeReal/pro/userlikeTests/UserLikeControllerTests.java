package com.ArtSeeReal.pro.userlikeTests;

import com.ArtSeeReal.pro.dto.user.UserCreateRequestDTO;
import com.ArtSeeReal.pro.enums.UserType;
import com.ArtSeeReal.pro.service.TokenService;
import com.ArtSeeReal.pro.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@SpringJUnitWebConfig
public class UserLikeControllerTests {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    private final List<UserCreateRequestDTO> authorDTOs = new ArrayList<>();
    private final List<UserCreateRequestDTO> plannerDTOs = new ArrayList<>();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        UserCreateRequestDTO createDTO = UserCreateRequestDTO.builder()
                .userId("testUserId")
                .name("테스트")
                .password("test1234")
                .nickname("testNickname")
                .email("test@gmail.com")
                .emailSecret(true)
                .phone("010-1234-5678")
                .phoneSecret(true)
                .userType(UserType.AUTHOR)
                .regDate(LocalDateTime.now())
                .build();
        String myUid = userService.createUser(createDTO).getUid();

        for (int i = 0; i < 10; i++) {
            UserCreateRequestDTO userCreateRequestDTO = UserCreateRequestDTO.builder()
                    .userId("testUserId" + i)
                    .name("테스트" + i)
                    .password("test1234")
                    .nickname("testNickname" + i)
                    .email("test" + i + "@gmail.com")
                    .emailSecret(true)
                    .phone("010-1234-5678")
                    .phoneSecret(true)
                    .userType(i % 2 == 1 ? UserType.AUTHOR : UserType.PLANNER)
                    .regDate(LocalDateTime.now())
                    .build();
            String yourUid = userService.createUser(userCreateRequestDTO).getUid();
            userService.userLikesCreate(myUid, yourUid);

            if (i % 2 == 1)
                authorDTOs.add(userCreateRequestDTO);
            else
                plannerDTOs.add(userCreateRequestDTO);
        }
    }

    @Test
    void 좋아요한_작가_조회() throws Exception {
        String aToken = tokenService.createToken("access", "testUserId", "AUTHOR", 600000L);
        Collections.reverse(authorDTOs);
        mockMvc.perform(get("/user/like/author")
                        .header("Authorization", aToken)
                        .header("Accept", MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(authorDTOs.size()));

        for (int i = 0; i < authorDTOs.size(); i++) {
            mockMvc.perform(get("/user/like/author")
                            .header("Authorization", aToken)
                            .header("Accept", MediaType.APPLICATION_JSON_VALUE)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.result[" + i + "].userId").value(authorDTOs.get(i).getUserId()))
                    .andExpect(jsonPath("$.result[" + i + "].userType").value(UserType.AUTHOR.getCode()))
                    .andExpect(jsonPath("$.result[" + i + "].nickname").value(authorDTOs.get(i).getNickname()));
        }
    }

    @Test
    void 좋아요한_플래너_조회() throws Exception {
        String pToken = tokenService.createToken("access", "testUserId", "AUTHOR", 600000L);
        Collections.reverse(plannerDTOs);
        mockMvc.perform(get("/user/like/planner")
                        .header("Authorization", pToken)
                        .header("Accept", MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.count").value(plannerDTOs.size()));

        for (int i = 0; i < plannerDTOs.size(); i++) {
            mockMvc.perform(get("/user/like/planner")
                            .header("Authorization", pToken)
                            .header("Accept", MediaType.APPLICATION_JSON_VALUE)
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.result[" + i + "].userId").value(plannerDTOs.get(i).getUserId()))
                    .andExpect(jsonPath("$.result[" + i + "].userType").value(UserType.PLANNER.getCode()))
                    .andExpect(jsonPath("$.result[" + i + "].nickname").value(plannerDTOs.get(i).getNickname()));
        }
    }
}
