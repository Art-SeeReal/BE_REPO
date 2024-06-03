package com.ArtSeeReal.pro.dto.recruitment;

import com.ArtSeeReal.pro.enums.CategoryType;
import com.ArtSeeReal.pro.enums.RegionType;

import java.util.Set;

public class AppliedRecruitInfoDTO {

    private String id;
    private String nickname;
    private String title;
    private String writerUid;
    private RegionType regions;
    private CategoryType fields;
    private Boolean isScrap;
    private Boolean isLike;
    private String userId;
    private Long applyTotalCount;

    public AppliedRecruitDTO isSetting(Set<String> favoriteRecuitmentSet, Set<String> userLikeSet){
        return AppliedRecruitDTO.builder()
                .id(id)
                .nickname(nickname)
                .title(title)
                .fields(fields.toCategoryInfo())
                .regions(regions.toRegionInfo())
                .isScrap(favoriteRecuitmentSet.contains(id))
                .isLike(userLikeSet.contains(writerUid))
                .userId(userId)
                .applyTotalCount(applyTotalCount)
                .build();
    }
}
