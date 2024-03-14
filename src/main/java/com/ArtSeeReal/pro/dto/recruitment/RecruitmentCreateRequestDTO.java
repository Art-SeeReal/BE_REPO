package com.ArtSeeReal.pro.dto.recruitment;

import com.ArtSeeReal.pro.entity.main.Recruitment;
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
public class RecruitmentCreateRequestDTO {

    private String uid;
    private String userUid;
    private Long viewCnt;
    private String title;
    private String content;
    private RegionType regionType;
    private Long category;
    private LocalDateTime regDate;
    private String thumbnail;
    private LocalDateTime dueDate;

    public Recruitment form(String uid){
        return Recruitment.builder()
                .uid(uid)
                .userUid(userUid)
                .viewCnt(0L)
                .title(title)
                .content(content)
                .regionType(regionType)
                .category(category)
                .regDate(LocalDateTime.now())
                .dueDate(dueDate)
                .build();
    }
}
