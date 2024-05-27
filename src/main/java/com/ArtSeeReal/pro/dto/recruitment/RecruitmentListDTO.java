package com.ArtSeeReal.pro.dto.recruitment;

import com.ArtSeeReal.pro.enums.enuminfo.CategoryInfo;
import com.ArtSeeReal.pro.enums.enuminfo.RegionInfo;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class RecruitmentListDTO {
    private String uid;
    private String imageUrl;
    private String title;
    private String nickname;
    private CategoryInfo field;
    private RegionInfo region;
    private boolean isScrap;
    private boolean isLike;
    private String userId;
}
