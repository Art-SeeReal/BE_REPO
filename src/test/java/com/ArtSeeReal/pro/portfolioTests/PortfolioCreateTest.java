package com.ArtSeeReal.pro.portfolioTests;

import com.ArtSeeReal.pro.dto.portfolio.PortfolioCreateRequestDTO;
import com.ArtSeeReal.pro.enums.CategoryType;
import com.ArtSeeReal.pro.enums.RegionType;
import com.ArtSeeReal.pro.service.PortfolioService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class PortfolioCreateTest {

    private final PortfolioService portfolioService;
    @Autowired
    public PortfolioCreateTest(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @Test
    public void 포트폴리오_만들기(){
        PortfolioCreateRequestDTO dto = PortfolioCreateRequestDTO.builder()
                .userUid("testUid")
                .title("testTitle")
                .content("testContent")
                .region(RegionType.SEOUL)
                .category(CategoryType.ART)
                .thumbnail("testThumbnail")
                .build();
        portfolioService.createPortfolio(dto);
    }
}
