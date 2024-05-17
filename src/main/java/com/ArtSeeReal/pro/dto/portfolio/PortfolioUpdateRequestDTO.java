package com.ArtSeeReal.pro.dto.portfolio;

import com.ArtSeeReal.pro.entity.history.PortfolioHistory;
import com.ArtSeeReal.pro.entity.main.Portfolio;
import com.ArtSeeReal.pro.enums.CategoryType;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PortfolioUpdateRequestDTO {

    private String uid;
    private String userUid;
    private String title;
    private String content;
    private CategoryType category;
    private String thumbnail;

    public PortfolioHistory createHistoryRecord(String uid, Portfolio portfolio){
        return PortfolioHistory.builder()
                .uid(uid)
                .boardUid(portfolio.getUid())
                .userUid(portfolio.getUserUid())
                .viewCnt(portfolio.getViewCnt())
                .title(title)
                .content(content)
                .category(category)
                .thumbnail(thumbnail)
                .regDate(portfolio.getRegDate())
                .exTitle(portfolio.getTitle())
                .exContent(portfolio.getContent())
                .exCategory(portfolio.getCategory())
                .exThumbnail(portfolio.getThumbnail())
                .modDate(LocalDateTime.now())
                .build();
    }
}
