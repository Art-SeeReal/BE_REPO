package com.ArtSeeReal.pro.dto.banner;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@Builder
@ToString
public class BannerCreateResponseDTO {
    private final String uid;
    private final String imageUrl;
    private final String linkUrl;
    private final LocalDateTime regDate;

}
