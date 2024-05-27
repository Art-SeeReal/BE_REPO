package com.ArtSeeReal.pro.portfolioTests;

import com.ArtSeeReal.pro.dto.portfolio.PortfolioCreateRequestDTO;
import com.ArtSeeReal.pro.dto.response.portfoilo.PortfolioReadResponseDTO;
import com.ArtSeeReal.pro.dto.user.UserCreateRequestDTO;
import com.ArtSeeReal.pro.enums.CategoryType;
import com.ArtSeeReal.pro.service.PortfolioService;
import com.ArtSeeReal.pro.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static com.ArtSeeReal.pro.enums.UserType.AUTHOR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class PortfolioReadTest {

    private final PortfolioService portfolioService;
    private final UserService userService;
    private String portfolioUid;
    private String userUid;
    @Autowired
    public PortfolioReadTest(PortfolioService portfolioService, UserService userService) {
        this.portfolioService = portfolioService;
        this.userService = userService;
    }

    @BeforeEach
    public void 공고_만들기(){
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

        userUid = userService.createUser(userDto).getUid();

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
    public void 포트폴리오_읽기(){
        PortfolioReadResponseDTO result = portfolioService.readPortfolio(portfolioUid,userUid);
        assertThat(result.getNickname()).isEqualTo("testNickname");

    }
}
