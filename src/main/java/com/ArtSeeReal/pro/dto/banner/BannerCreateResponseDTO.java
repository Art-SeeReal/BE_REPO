package com.ArtSeeReal.pro.dto.banner;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@ToString
public class BannerCreateResponseDTO {
    private final String uid;
    private final byte[] imageData;
    private final String linkUrl;
    private final LocalDateTime regDate;

}
