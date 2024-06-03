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
public class ApplyRecruitDTO {

    private String id;
    private String nickname;
    private String title;
    private RegionInfo regions;
    private CategoryInfo fields;
    private Boolean isScrap;
    private Boolean isLike;
    private String userId;
}
