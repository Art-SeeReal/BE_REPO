package com.ArtSeeReal.pro.bannerTests;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.ArtSeeReal.pro.dto.banner.BannerCreateRequestDTO;
import com.ArtSeeReal.pro.dto.banner.BannerCreateResponseDTO;
import com.ArtSeeReal.pro.dto.banner.BannerReadResponseDTO;
import com.ArtSeeReal.pro.service.BannerService;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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

        uid = bannerService.createBanner(dto)
                .getUid();
    }

    @Test
    void 배너_읽어오기(){
        BannerReadResponseDTO result = bannerService.readBanner(uid);
        assertThat(result.getLinkUrl()).isEqualTo("testUrl");
    }
}
