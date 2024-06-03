package com.ArtSeeReal.pro.dto.recruitment;

import com.ArtSeeReal.pro.enums.CategoryType;
import com.ArtSeeReal.pro.enums.RegionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecruitmentReadDTO {
    private String id;
    private String imageUrl;
    private String title;
    private String nickname;
    private RegionType region;
    private CategoryType field;
    private boolean isScrap;
    private boolean isLike;
    private String writerUid;
    private String userId;
    private Long view;
    private LocalDateTime regDate;
    private LocalDateTime dueDate;
    private Long payment;
    private String content;
}
