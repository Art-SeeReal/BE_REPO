package com.ArtSeeReal.pro.bannerTests;

import com.ArtSeeReal.pro.dto.banner.BannerCreateRequestDTO;
import com.ArtSeeReal.pro.service.BannerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@SpringBootTest
@Transactional
public class BannerDeleteTest {

    public final BannerService bannerService;
    private String uid;
    @Autowired
    public BannerDeleteTest(BannerService bannerService) {
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
    void 배너_삭제하기(){
        bannerService.deleteBanner(uid);
    }
}
