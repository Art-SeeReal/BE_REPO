package com.ArtSeeReal.pro.bannerTests;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.ArtSeeReal.pro.dto.banner.BannerCreateRequestDTO;
import com.ArtSeeReal.pro.dto.banner.BannerReadResponseDTO;
import com.ArtSeeReal.pro.service.BannerService;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
            String content = "This is a dummy file content. " + i;
            String fileName = i + " dummy.txt";
            String contentType = "text/plain";

            MultipartFile file = new MockMultipartFile(
                    fileName,
                    fileName,
                    contentType,
                    content.getBytes(StandardCharsets.UTF_8));
            String testUrl = "testUrl " + i;

            BannerCreateRequestDTO dto = BannerCreateRequestDTO.builder()
                    .linkUrl(testUrl)
                    .imageData(file)
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
