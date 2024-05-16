package com.ArtSeeReal.pro.portfolioTests;

import com.ArtSeeReal.pro.dto.portfolio.PortfolioCreateRequestDTO;
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

@SpringBootTest
@Transactional
public class PortfolioFavoriteCreateTest {

    private final PortfolioService portfolioService;
    private final UserService userService;
    private String portfolioUid;
    private String userUid;

    @Autowired
    public PortfolioFavoriteCreateTest(PortfolioService portfolioService, UserService userService) {
        this.portfolioService = portfolioService;
        this.userService = userService;
    }

    @BeforeEach
    void 더미데이터_생성(){
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

        userUid = userService.createUser(userCreateRequestDTO).getUid();

        PortfolioCreateRequestDTO dto = PortfolioCreateRequestDTO.builder()
                .userUid("testUid")
                .title("testTitle")
                .content("testContent")
                .category(CategoryType.CRAFT)
                .thumbnail("testThumbnail")
                .build();

        portfolioUid = portfolioService.createPortfolio(dto).getUid();
    }

    @Test
    void 포트폴리오_즐겨찾기_성공(){
        portfolioService.favoritePortfolioCreate(userUid,portfolioUid);
    }
}
