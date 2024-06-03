package com.ArtSeeReal.pro.dto.recruitment;

import com.ArtSeeReal.pro.enums.CategoryType;
import com.ArtSeeReal.pro.enums.RegionType;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ApplyRecruitInfoDTO {

    private String id;
    private String nickname;
    private String title;
    private String writerUid;
    private RegionType regions;
    private CategoryType fields;
    private Boolean isScrap;
    private Boolean isLike;
    private String userId;

    public ApplyRecruitDTO isSetting(Set<String> favoriteRecuitmentSet, Set<String> userLikeSet){
        return ApplyRecruitDTO.builder()
                .id(id)
                .nickname(nickname)
                .title(title)
                .fields(fields.toCategoryInfo())
                .regions(regions.toRegionInfo())
                .isScrap(favoriteRecuitmentSet.contains(id))
                .isLike(userLikeSet.contains(writerUid))
                .userId(userId)
                .build();
    }
}
