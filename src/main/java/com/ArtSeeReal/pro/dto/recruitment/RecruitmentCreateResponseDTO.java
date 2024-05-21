package com.ArtSeeReal.pro.dto.recruitment;

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

    private String uid;
    private String userUid;
    private Long viewCnt;
    private String title;
    private String content;
    private RegionType region;
    private CategoryType category;
    private LocalDateTime regDate;
    private String thumbnail;
    private LocalDateTime dueDate;
    private Long payment;

}
