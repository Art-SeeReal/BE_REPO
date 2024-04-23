package com.ArtSeeReal.pro.portfolioTests;

import com.ArtSeeReal.pro.dto.portfolio.PortfolioCreateRequestDTO;
import com.ArtSeeReal.pro.enums.CategoryType;
import com.ArtSeeReal.pro.service.PortfolioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class PortfolioDeleteTest {

    private final PortfolioService portfolioService;
    private String uid;
    @Autowired
    public PortfolioDeleteTest(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @BeforeEach
    public void 공고_만들기(){
        PortfolioCreateRequestDTO dto = PortfolioCreateRequestDTO.builder()
                .userUid("testUid")
                .title("testTitle")
                .content("testContent")
                .category(CategoryType.ART)
                .thumbnail("testThumbnail")
                .build();
        uid = portfolioService.createPortfolio(dto).getUid();
    }
    @Test
    public void 포트폴리오_삭제(){
        portfolioService.deletePortfolio(uid);
    }
}
