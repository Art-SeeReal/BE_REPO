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
public class PortfolioDeleteTest {

    private final PortfolioService portfolioService;
    private final UserService userService;
    private String uid;
    private String userUid;
    @Autowired
    public PortfolioDeleteTest(PortfolioService portfolioService, UserService userService) {
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

        String userId = userService.createUser(userDto).getUserId();
        userUid = userService.getUserUid(userId);


        PortfolioCreateRequestDTO dto = PortfolioCreateRequestDTO.builder()
                .userUid(userUid)
                .title("testTitle")
                .content("testContent")
                .category(CategoryType.ART)
                .thumbnail("testThumbnail")
                .build();
        uid = portfolioService.createPortfolio(dto).getUid();
    }
    @Test
    public void 포트폴리오_삭제(){
        portfolioService.deletePortfolio(uid,userUid);
    }
}
