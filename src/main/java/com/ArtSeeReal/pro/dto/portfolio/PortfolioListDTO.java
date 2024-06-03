package com.ArtSeeReal.pro.dto.portfolio;

import com.ArtSeeReal.pro.enums.enuminfo.CategoryInfo;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PortfolioListDTO {
    private String uid;
    private String imageUrl;
    private String title;
    private String nickname;
    private CategoryInfo field;
    private boolean isScrap;
    private boolean isLike;
    private String userId;
}
