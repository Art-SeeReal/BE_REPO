package com.ArtSeeReal.pro.dto.response.portfoilo;

import com.ArtSeeReal.pro.enums.CategoryType;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PortfolioCreateResponseDTO {

    private String uid;
    private String userUid;
    private Long viewCnt;
    private String title;
    private String content;
    private CategoryType category;
    private LocalDateTime regDate;
    private String thumbnail;

}
