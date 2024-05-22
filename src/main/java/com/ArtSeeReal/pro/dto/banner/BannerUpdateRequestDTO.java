package com.ArtSeeReal.pro.dto.banner;

import com.ArtSeeReal.pro.entity.main.Banner;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@Builder
@ToString
public class BannerUpdateRequestDTO {
    private final String uid;
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
