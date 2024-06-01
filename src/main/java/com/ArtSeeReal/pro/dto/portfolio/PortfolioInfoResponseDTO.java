package com.ArtSeeReal.pro.dto.portfolio;

import com.ArtSeeReal.pro.enums.CategoryType;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PortfolioInfoResponseDTO {

    private String uid;
    private String userUid;
    private String nickname;
    private Long viewCnt;
    private String title;
    private String content;
    private CategoryType category;
    private LocalDateTime regDate;
    private String thumbnail;

}
