package com.ArtSeeReal.pro.dto.response.recruitment;

import com.ArtSeeReal.pro.enums.CategoryType;
import com.ArtSeeReal.pro.enums.RegionType;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RecruitmentCreateResponseDTO {

    private String userUid;
    private Long viewCnt;
    private String title;
    private String content;
    private RegionType region;
    private CategoryType category;
    private String thumbnail;
    private LocalDateTime regDate;
    private LocalDateTime dueDate;
    private Long payment;

}
