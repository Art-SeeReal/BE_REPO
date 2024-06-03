package com.ArtSeeReal.pro.portfolioTests;

import com.ArtSeeReal.pro.dto.request.portfolio.PortfolioCreateRequestDTO;
import com.ArtSeeReal.pro.dto.user.UserCreateRequestDTO;
import com.ArtSeeReal.pro.enums.CategoryType;
import com.ArtSeeReal.pro.service.PortfolioService;
import com.ArtSeeReal.pro.service.TokenService;
import com.ArtSeeReal.pro.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;

import static com.ArtSeeReal.pro.enums.UserType.AUTHOR;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
public class PortfolioReadTest {

    private MockMvc mockMvc;

    private final WebApplicationContext webApplicationContext;
    private final PortfolioService portfolioService;
    private final UserService userService;
    private final TokenService tokenService;
    private String portfolioUid;

    @Autowired
    public PortfolioReadTest(WebApplicationContext webApplicationContext, PortfolioService portfolioService, UserService userService, TokenService tokenService) {
        this.webApplicationContext = webApplicationContext;
        this.portfolioService = portfolioService;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        createDummyData();
    }

    void createDummyData() {
        UserCreateRequestDTO userDto = UserCreateRequestDTO
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

        String userUid = userService.createUser(userDto).getUid();

        PortfolioCreateRequestDTO portfolioDto = PortfolioCreateRequestDTO.builder()
                .userUid(userUid)
                .title("testTitle")
                .content("testContent")
                .category(CategoryType.ART)
                .thumbnail("testThumbnail")
                .build();

        portfolioUid = portfolioService.createPortfolio(portfolioDto).getUid();
    }

    @Test
    public void 포트폴리오_읽기() throws Exception {
        String aToken = tokenService.createToken("access", "test", "AUTHOR", 600000L);

        MvcResult result = mockMvc.perform(get("/portfolios")
                        .header("Authorization", aToken)
                        .param("portId", portfolioUid)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println("반환데이터: " + content);
    }

    @Test
    public void 포트폴리오_리스트_읽기() throws Exception {
        String aToken = tokenService.createToken("access", "test", "AUTHOR", 600000L);
        // Add any necessary fields to the dto if needed

        MvcResult result = mockMvc.perform(get("/portfolios")
                        .header("Authorization", aToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println("반환데이터: " + content);
    }
}

