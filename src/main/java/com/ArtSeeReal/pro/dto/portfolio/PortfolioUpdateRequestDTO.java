package com.ArtSeeReal.pro.dto.portfolio;

import com.ArtSeeReal.pro.entity.history.PortfolioHistory;
import com.ArtSeeReal.pro.entity.main.Portfolio;
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
    private RegionType regionType;
    private Long category;
    private String thumbnail;

    public PortfolioHistory createHistoryRecord(String uid, Portfolio portfolio, String modUserUid){
        return PortfolioHistory.builder()
                .uid(uid)
                .boardUid(portfolio.getUid())
                .userUid(portfolio.getUserUid())
                .viewCnt(portfolio.getViewCnt())
                .oldTitle(portfolio.getTitle())
                .newTitle(title)
                .oldContent(portfolio.getContent())
                .newContent(content)
                .oldRegionType(portfolio.getRegionType())
                .newRegionType(regionType)
                .oldCategory(portfolio.getCategory())
                .newCategory(category)
                .oldThumbnail(portfolio.getThumbnail())
                .newThumbnail(thumbnail)
                .modDate(LocalDateTime.now())
                .modUserUid(modUserUid)
                .build();
    }
}
