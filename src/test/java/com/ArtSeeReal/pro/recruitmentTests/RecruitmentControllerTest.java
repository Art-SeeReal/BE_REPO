package com.ArtSeeReal.pro.recruitmentTests;


import com.ArtSeeReal.pro.dto.request.recuitment.RecruitmentCreateRequestDTO;
import com.ArtSeeReal.pro.dto.user.UserCreateRequestDTO;
import com.ArtSeeReal.pro.enums.CategoryType;
import com.ArtSeeReal.pro.enums.RegionType;
import com.ArtSeeReal.pro.enums.UserType;
import com.ArtSeeReal.pro.service.RecruitmentService;
import com.ArtSeeReal.pro.service.TokenService;
import com.ArtSeeReal.pro.service.UserService;
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
import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@SpringJUnitWebConfig
public class RecruitmentControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserService userService;

    @Autowired
    private RecruitmentService recruitmentService;

    @Autowired
    private TokenService tokenService;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//        createDummyData();
    }

    private void createDummyData() {
        // Create recruitment writing user
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

        // Create searching users and portfolios
        for (int i = 11; i <= 20; i++) {
            // Create searching user
            UserCreateRequestDTO userCreateRequestDTO = UserCreateRequestDTO.builder()
                    .userId("user" + i)
                    .name("글 쓰는 유저 이름" + i)
                    .password("test1234" + i)
                    .nickname("글 쓰는 유저 닉네임" + i)
                    .email("user" + i + "@gmail.com")
                    .emailSecret(true)
                    .phone("010-1234-5678" + i)
                    .phoneSecret(true)
                    .userType(UserType.AUTHOR)
                    .regDate(LocalDateTime.now())
                    .build();

            String WriterUid = userService.createUser(userCreateRequestDTO).getUid();

            if (new Random().nextBoolean())
                userService.userLikesCreate(readUserUid, WriterUid);
            // Create portfolios and add random likes
            for (int j = 1; j <= 100; j++) {
                CategoryType randomCategory = CategoryType.values()[new Random().nextInt(CategoryType.values().length)];
                RegionType randomRegion = RegionType.values()[new Random().nextInt(RegionType.values().length)];

                RecruitmentCreateRequestDTO dto = RecruitmentCreateRequestDTO.builder()
                        .userUid(WriterUid)
                        .title("공고 제목 " + i + " - " + j)
                        .content("공고 내용 " + i + " - " + j)
                        .region(randomRegion)
                        .category(randomCategory)
                        .thumbnail("공고 섬네일 " + i + " - " + j)
                        .dueDate(LocalDateTime.now())
                        .payment(new Random().nextLong(10000)*10000)
                        .build();

                String recruitmentUid = recruitmentService.createRecruitment(dto).getUid();

                if (new Random().nextBoolean())
                    recruitmentService.favoriteRecruitmentCreate(readUserUid, recruitmentUid);
            }
        }
    }

    @Test
    void 토큰이_없을_때() throws Exception {
        MvcResult result = mockMvc.perform(get("/recruits")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println("토큰이 없을 때 반환된 데이터: " + content);
    }

    @Test
    void 토큰이_있을_때() throws Exception {
        String aToken = tokenService.createToken("access", "recruitmentWriter", "PLANNER", 600000L);
        System.out.println(aToken);
        MvcResult result = mockMvc.perform(get("/recruits")
                        .header("Authorization", aToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println("토큰이 있을 때 반환된 데이터: " + content);
    }
}
