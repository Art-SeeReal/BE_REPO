package com.ArtSeeReal.pro.dto.portfolio;

import com.ArtSeeReal.pro.entity.history.PortfolioHistory;
import com.ArtSeeReal.pro.entity.main.Portfolio;
import com.ArtSeeReal.pro.enums.CategoryType;
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
public class PortfolioUpdateRequestDTO {

    private String uid;
    private String title;
    private String content;
    private RegionType region;
    private CategoryType category;
    private String thumbnail;

    public PortfolioHistory createHistoryRecord(String uid, Portfolio portfolio, String modUserUid){
        return PortfolioHistory.builder()
                .uid(uid)
                .boardUid(portfolio.getUid())
                .userUid(portfolio.getUserUid())
                .viewCnt(portfolio.getViewCnt())
                .title(title)
                .content(content)
                .region(region)
                .category(category)
                .thumbnail(thumbnail)
                .regDate(portfolio.getRegDate())
                .exTitle(portfolio.getTitle())
                .exContent(portfolio.getContent())
                .exRegionType(portfolio.getRegion())
                .exCategory(portfolio.getCategory())
                .exThumbnail(portfolio.getThumbnail())
                .modDate(LocalDateTime.now())
                .modUserUid(modUserUid)
                .build();
    }
}
