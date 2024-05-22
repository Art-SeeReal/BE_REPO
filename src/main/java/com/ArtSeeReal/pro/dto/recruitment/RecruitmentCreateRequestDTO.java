package com.ArtSeeReal.pro.dto.recruitment;

import com.ArtSeeReal.pro.entity.main.Recruitment;
import com.ArtSeeReal.pro.enums.CategoryType;
import com.ArtSeeReal.pro.enums.RegionType;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class RecruitmentCreateRequestDTO {

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

    public Recruitment form(String uid){
        return Recruitment.builder()
                .uid(uid)
                .userUid(userUid)
                .viewCnt(0L)
                .title(title)
                .content(content)
                .region(region)
                .category(category)
                .regDate(LocalDateTime.now())
                .dueDate(dueDate)
                .payment(payment)
                .build();
    }
}
