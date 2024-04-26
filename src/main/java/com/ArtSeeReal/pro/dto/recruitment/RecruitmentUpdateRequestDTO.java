package com.ArtSeeReal.pro.dto.recruitment;

import com.ArtSeeReal.pro.entity.history.RecruitmentHistory;
import com.ArtSeeReal.pro.entity.main.Recruitment;
import com.ArtSeeReal.pro.enums.CategoryType;
import com.ArtSeeReal.pro.enums.RegionType;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RecruitmentUpdateRequestDTO {

    private String uid;
    private String title;
    private String content;
    private RegionType region;
    private CategoryType category;
    private String thumbnail;
    private LocalDateTime dueDate;
    private Long payment;

    public RecruitmentHistory createHistoryRecord(String uid, Recruitment recruitment, String modUserUid){
        return RecruitmentHistory.builder()
                .uid(uid)
                .boardUid(recruitment.getUid())
                .userUid(recruitment.getUserUid())
                .viewCnt(recruitment.getViewCnt())
                .title(title)
                .content(content)
                .region(region)
                .category(category)
                .thumbnail(thumbnail)
                .dueDate(dueDate)
                .payment(payment)
                .regDate(recruitment.getRegDate())
                .exTitle(recruitment.getTitle())
                .exContent(recruitment.getContent())
                .exRegionType(recruitment.getRegion())
                .exCategory(recruitment.getCategory())
                .exThumbnail(recruitment.getThumbnail())
                .exDueDate(recruitment.getDueDate())
                .exPayment(recruitment.getPayment())
                .modDate(LocalDateTime.now())
                .modUserUid(modUserUid)
                .build();
    }
}
