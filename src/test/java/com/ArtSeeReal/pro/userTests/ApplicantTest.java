package com.ArtSeeReal.pro.userTests;

import com.ArtSeeReal.pro.dto.request.recuitment.RecruitmentCreateRequestDTO;
import com.ArtSeeReal.pro.dto.user.UserCreateRequestDTO;
import com.ArtSeeReal.pro.enums.CategoryType;
import com.ArtSeeReal.pro.enums.RegionType;
import com.ArtSeeReal.pro.enums.UserType;
import com.ArtSeeReal.pro.service.RecruitmentService;
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
import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@SpringJUnitWebConfig
public class ApplicantTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;
    private final UserService userService;
    private final RecruitmentService recruitmentService;

    @Autowired
    public ApplicantTest(UserService userService, RecruitmentService recruitmentService) {
        this.userService = userService;
        this.recruitmentService = recruitmentService;
    }

    private String postUid;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        createDummyData();
    }

    void createDummyData(){
        RecruitmentCreateRequestDTO dto = RecruitmentCreateRequestDTO.builder()
                .userUid("testUid")
                .title("testTitle")
                .content("testContent")
                .region(RegionType.SEOUL)
                .category(CategoryType.ART)
                .thumbnail("testThumbnail")
                .dueDate(LocalDateTime.now())
                .payment(1000000L)
                .build();
        postUid = recruitmentService.createRecruitment(dto).getUid();

        for (int i = 0; i < 50; i++) {
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

            if (new Random().nextBoolean())
                recruitmentService.applyRecruitmentCreate(readUserUid, postUid);
        }
    }

    @Test
    void 지원자목록() throws Exception {
        MvcResult result = mockMvc.perform(get("/user/apply/planner/{postId}", postUid)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        // Optionally, print the response for debugging
        String content = result.getResponse().getContentAsString();
        System.out.println("Response: " + content);
    }

}
