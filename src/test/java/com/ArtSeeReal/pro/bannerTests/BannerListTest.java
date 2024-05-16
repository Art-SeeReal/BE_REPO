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
public class BannerListTest {

    public final BannerService bannerService;
    @Autowired
    public BannerListTest(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @BeforeEach
    void 배너_생성() throws IOException {
        for (int i = 0; i < 1; i++) {
            String testUrl = "testUrl";
            String imageUrl = "testImageUrl";

            BannerCreateRequestDTO dto = BannerCreateRequestDTO.builder()
                    .linkUrl(testUrl)
                    .imageUrl(imageUrl)
                    .build();

            bannerService.createBanner(dto);
        }
    }

    @Test
    void 배너_읽어오기(){
//        List<BannerReadResponseDTO> result = bannerService.bannerList();
//        for (BannerReadResponseDTO dto : result)
//            System.out.println(dto.toString());
    }
}
