package com.ArtSeeReal.pro.bannerTests;

import com.ArtSeeReal.pro.dto.banner.BannerCreateRequestDTO;
import com.ArtSeeReal.pro.dto.banner.BannerCreateResponseDTO;
import com.ArtSeeReal.pro.service.BannerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class BannerCreateTest {

    public final BannerService bannerService;
    @Autowired
    public BannerCreateTest(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @Test
    void 배너_생성() throws IOException {
        String testUrl = "testUrl";
        String imageUrl = "testImageUrl";

        BannerCreateRequestDTO dto = BannerCreateRequestDTO.builder()
                .linkUrl(testUrl)
                .imageUrl(imageUrl)
                .build();

        BannerCreateResponseDTO result = bannerService.createBanner(dto);

        assertThat(result.getLinkUrl()).isEqualTo(testUrl);
    }
}
