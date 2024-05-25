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
public class RecruitmentInfoDTO {
    private String uid;
    private String imageUrl;
    private String title;
    private String nickname;
    private String writerUid;
    private CategoryType field;
    private RegionType region;
    private boolean isScrap;
    private boolean isLike;
    private String userUid;
    private String userId;

    public RecruitmentReadDTO isSetting(Set<String> favoriteRecuitmentSet, Set<String> userLikeSet){
        return RecruitmentReadDTO.builder()
                .uid(uid)
                .imageUrl(imageUrl)
                .title(title)
                .nickname(nickname)
                .field(field.toCategoryInfo())
                .region(region.toRegionInfo())
                .isScrap(favoriteRecuitmentSet.contains(uid))
                .isLike(userLikeSet.contains(writerUid))
                .userId(userId)
                .build();
    }

    public RecruitmentReadDTO infoToReadDTO(){
        return RecruitmentReadDTO.builder()
                .uid(uid)
                .imageUrl(imageUrl)
                .title(title)
                .nickname(nickname)
                .field(field.toCategoryInfo())
                .region(region.toRegionInfo())
                .isScrap(isScrap)
                .isLike(isLike)
                .userId(userId)
                .build();
    }
}
