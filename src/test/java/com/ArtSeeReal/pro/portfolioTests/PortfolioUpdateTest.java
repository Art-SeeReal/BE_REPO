package com.ArtSeeReal.pro.portfolioTests;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.ArtSeeReal.pro.dto.portfolio.PortfolioCreateRequestDTO;
import com.ArtSeeReal.pro.dto.portfolio.PortfolioReadResponseDTO;
import com.ArtSeeReal.pro.dto.portfolio.PortfolioUpdateRequestDTO;
import com.ArtSeeReal.pro.enums.CategoryType;
import com.ArtSeeReal.pro.enums.RegionType;
import com.ArtSeeReal.pro.service.PortfolioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class PortfolioUpdateTest {

    private final PortfolioService portfolioService;
    private String uid;
    @Autowired
    public PortfolioUpdateTest(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @BeforeEach
    public void 공고_만들기(){
        PortfolioCreateRequestDTO dto = PortfolioCreateRequestDTO.builder()
                .userUid("testUid")
                .title("testTitle")
                .content("testContent")
                .region(RegionType.SEOUL)
                .category(CategoryType.ART)
                .thumbnail("testThumbnail")
                .build();
        uid = portfolioService.createPortfolio(dto).getUid();
    }
    @Test
    public void 포트폴리오_업데이트(){
        PortfolioUpdateRequestDTO dto = PortfolioUpdateRequestDTO.builder()
                .uid(uid)
                .title("UpdateTitle")
                .content("UpdateContent")
                .region(RegionType.BUSAN)
                .category(CategoryType.ART)
                .thumbnail("UpdateThumbnail")
                .build();
        PortfolioReadResponseDTO result = portfolioService.updatePortfolio(dto);
        assertThat(result.getContent()).isEqualTo("UpdateContent");

    }
}
