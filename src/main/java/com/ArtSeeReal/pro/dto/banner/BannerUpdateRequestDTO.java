package com.ArtSeeReal.pro.dto.banner;

import com.ArtSeeReal.pro.entity.main.Banner;
import java.io.IOException;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@ToString
public class BannerUpdateRequestDTO {
    private final String uid;
    private final MultipartFile imageData;
    private final String linkUrl;

    public Banner toEntity(String uid) throws IOException {
        return Banner.builder()
                .uid(uid)
                .imageData(imageData.getBytes())
                .linkUrl(linkUrl)
                .regDate(LocalDateTime.now())
                .build();
    }
}
