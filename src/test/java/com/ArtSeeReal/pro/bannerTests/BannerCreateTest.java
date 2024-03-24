package com.ArtSeeReal.pro.bannerTests;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.ArtSeeReal.pro.dto.banner.BannerCreateRequestDTO;
import com.ArtSeeReal.pro.dto.banner.BannerCreateResponseDTO;
import com.ArtSeeReal.pro.service.BannerService;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
        String content = "This is a dummy file content.";
        String fileName = "dummy.txt";
        String contentType = "text/plain";

        MultipartFile file = new MockMultipartFile(
                fileName,
                fileName,
                contentType,
                content.getBytes(StandardCharsets.UTF_8));
        String testUrl = "testUrl";

        BannerCreateRequestDTO dto = BannerCreateRequestDTO.builder()
                .linkUrl(testUrl)
                .imageData(file)
                .build();

        BannerCreateResponseDTO result = bannerService.createBanner(dto);

        assertThat(result.getLinkUrl()).isEqualTo(testUrl);
    }
}
