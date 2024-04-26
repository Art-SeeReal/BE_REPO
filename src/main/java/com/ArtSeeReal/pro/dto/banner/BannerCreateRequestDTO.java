package com.ArtSeeReal.pro.dto.banner;

import com.ArtSeeReal.pro.entity.main.Banner;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class BannerCreateRequestDTO {
    private final String imageUrl;
    private final String linkUrl;

    public Banner toEntity(String uid) {
        return Banner.builder()
                .uid(uid)
                .imageUrl(imageUrl)
                .linkUrl(linkUrl)
                .regDate(LocalDateTime.now())
                .build();
    }
}
