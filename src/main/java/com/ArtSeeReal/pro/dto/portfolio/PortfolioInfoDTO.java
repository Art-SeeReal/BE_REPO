package com.ArtSeeReal.pro.dto.portfolio;

import com.ArtSeeReal.pro.enums.CategoryType;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PortfolioInfoDTO {
    private String uid;
    private String imageUrl;
    private String title;
    private String nickname;
    private String writerUid;
    private CategoryType field;
    private boolean isScrap;
    private boolean isLike;
    private String userUid;
    private String userId;

    public PortfolioListDTO isSetting(Set<String> favoritePortfolioSet, Set<String> userLikeSet){
        return PortfolioListDTO.builder()
                .uid(uid)
                .imageUrl(imageUrl)
                .title(title)
                .nickname(nickname)
                .field(field.toCategoryInfo())
                .isScrap(favoritePortfolioSet.contains(uid))
                .isLike(userLikeSet.contains(writerUid))
                .userId(userId)
                .build();
    }

    public PortfolioListDTO userLikeSetting(Set<String> userLikeSet){
        return PortfolioListDTO.builder()
                .uid(uid)
                .imageUrl(imageUrl)
                .title(title)
                .nickname(nickname)
                .field(field.toCategoryInfo())
                .isScrap(isScrap)
                .isLike(userLikeSet.contains(writerUid))
                .userId(userId)
                .build();
    }

    public PortfolioListDTO infoToReadDTO(){
        return PortfolioListDTO.builder()
                .uid(uid)
                .imageUrl(imageUrl)
                .title(title)
                .nickname(nickname)
                .field(field.toCategoryInfo())
                .isScrap(isScrap)
                .isLike(isLike)
                .userId(userId)
                .build();
    }
}
