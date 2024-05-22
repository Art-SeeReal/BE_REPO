package com.ArtSeeReal.pro.bannerTests;

import com.ArtSeeReal.pro.dto.banner.BannerCreateRequestDTO;
import com.ArtSeeReal.pro.dto.banner.BannerReadResponseDTO;
import com.ArtSeeReal.pro.service.BannerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class BannerReadTest {

    public final BannerService bannerService;
    private String uid;
    @Autowired
    public BannerReadTest(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @BeforeEach
    void 배너_생성() throws IOException {
        String testUrl = "testUrl";
        String imageUrl = "testImageUrl";

        BannerCreateRequestDTO dto = BannerCreateRequestDTO.builder()
                .linkUrl(testUrl)
                .imageUrl(imageUrl)
                .build();

        uid = bannerService.createBanner(dto)
                .getUid();
    }

    @Test
    void 배너_읽어오기(){
        BannerReadResponseDTO result = bannerService.readBanner(uid);
        assertThat(result.getLinkUrl()).isEqualTo("testUrl");
    }
}
