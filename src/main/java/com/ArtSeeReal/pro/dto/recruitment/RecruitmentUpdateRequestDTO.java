package com.ArtSeeReal.pro.dto.recruitment;

import com.ArtSeeReal.pro.entity.history.RecruitmentHistory;
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
public class RecruitmentUpdateRequestDTO {

    private String uid;
    private String title;
    private String content;
    private RegionType regionType;
    private Long category;
    private String thumbnail;
    private LocalDateTime dueDate;

    public RecruitmentHistory createHistoryRecord(String uid, Recruitment recruitment, String modUserUid){
        return RecruitmentHistory.builder()
                .uid(uid)
                .boardUid(recruitment.getUid())
                .userUid(recruitment.getUserUid())
                .viewCnt(recruitment.getViewCnt())
                .oldTitle(recruitment.getTitle())
                .newTitle(title)
                .oldContent(recruitment.getContent())
                .newContent(content)
                .oldRegionType(recruitment.getRegionType())
                .newRegionType(regionType)
                .oldCategory(recruitment.getCategory())
                .newCategory(category)
                .oldThumbnail(recruitment.getThumbnail())
                .newThumbnail(thumbnail)
                .oldDueDate(recruitment.getDueDate())
                .newDueDate(dueDate)
                .modDate(LocalDateTime.now())
                .modUserUid(modUserUid)
                .build();
    }
}
