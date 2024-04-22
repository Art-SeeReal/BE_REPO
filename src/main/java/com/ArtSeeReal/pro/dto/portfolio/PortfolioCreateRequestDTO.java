package com.ArtSeeReal.pro.dto.portfolio;

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
public class PortfolioCreateRequestDTO {

    private String uid;
    private String userUid;
    private Long viewCnt;
    private String title;
    private String content;
    private RegionType region;
    private CategoryType category;
    private LocalDateTime regDate;
    private String thumbnail;

    public Portfolio form(String uid){
        return Portfolio.builder()
                .uid(uid)
                .userUid(userUid)
                .viewCnt(0L)
                .title(title)
                .content(content)
                .region(region)
                .category(category)
                .regDate(LocalDateTime.now())
                .build();
    }
}
