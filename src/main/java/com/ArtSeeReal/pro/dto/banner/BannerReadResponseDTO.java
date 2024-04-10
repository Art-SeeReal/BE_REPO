package com.ArtSeeReal.pro.dto.banner;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class BannerReadResponseDTO {
    private final String uid;
    private final byte[] imageData;
    private final String linkUrl;
    private final LocalDateTime regDate;

}
