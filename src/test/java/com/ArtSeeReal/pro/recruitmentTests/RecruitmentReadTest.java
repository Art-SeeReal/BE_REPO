package com.ArtSeeReal.pro.recruitmentTests;

import com.ArtSeeReal.pro.dto.recruitment.RecruitmentCreateRequestDTO;
import com.ArtSeeReal.pro.dto.request.recuitment.RecruitmentListRequestDTO;
import com.ArtSeeReal.pro.dto.response.recruitment.RecruitmentReadResponseDTO;
import com.ArtSeeReal.pro.dto.user.UserCreateRequestDTO;
import com.ArtSeeReal.pro.enums.CategoryType;
import com.ArtSeeReal.pro.enums.RegionType;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.util.HashMap;

import static com.ArtSeeReal.pro.enums.UserType.AUTHOR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@SpringJUnitWebConfig
public class RecruitmentReadTest {

    private MockMvc mockMvc;

    private final WebApplicationContext webApplicationContext;
    private final RecruitmentService recruitService;
    private final UserService userService;
    private final TokenService tokenService;
    private String uid;

    @Autowired
    public RecruitmentReadTest(WebApplicationContext webApplicationContext, RecruitmentService recruitService, UserService userService, TokenService tokenService) {
        this.webApplicationContext = webApplicationContext;
        this.recruitService = recruitService;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        createDummyData();
    }

    void createDummyData() {
        UserCreateRequestDTO userCreateRequestDTO = UserCreateRequestDTO
                .builder()
                .userId("test")
                .name("테스트")
                .password("test1234")
                .nickname("testNickname")
                .email("test@gmail.com")
                .emailSecret(true)
                .phone("010-1234-5678")
                .phoneSecret(true)
                .userType(AUTHOR)
                .regDate(LocalDateTime.now())
                .build();

        String userUid = userService.createUser(userCreateRequestDTO).getUid();

        RecruitmentCreateRequestDTO dto = RecruitmentCreateRequestDTO.builder()
                .userUid(userUid)
                .title("testTitle")
                .content("testContent")
                .region(RegionType.SEOUL)
                .category(CategoryType.ART)
                .thumbnail("testThumbnail")
                .dueDate(LocalDateTime.now())
                .payment(1000000L)
                .build();
        uid = recruitService.createRecruitment(dto).getUid();
    }

    @Test
    public void 공고_읽어오기() throws Exception {
        String aToken = tokenService.createToken("access", "test", "AUTHOR", 600000L);

        MvcResult result = mockMvc.perform(get("/recruits")
                        .header("Authorization", aToken)
                        .param("portId", uid)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println("반환데이터: " + content);

        // Optional: Validate the content of the response
        assertThat(content).contains(uid);
    }

    @Test
    public void 공고_리스트_읽어오기() throws Exception {
        String aToken = tokenService.createToken("access", "test", "AUTHOR", 600000L);
        // Add any necessary fields to the dto if needed

        MvcResult result = mockMvc.perform(get("/recruits")
                        .header("Authorization", aToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println("반환데이터: " + content);
    }
}

