package com.ArtSeeReal.pro.dto.portfolio;

import com.ArtSeeReal.pro.enums.CategoryType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PortfolioReadDTO {
    private String id;
    private String imageUrl;
    private String title;
    private String nickname;
    private CategoryType fields; // 별건 아닌데 단수로 바꿔야 하는거 아닌가? 하난데
    private boolean isScrap;
    private boolean isLike;
    private String writerUid;
    private String userId;
    private Long view;
    private LocalDateTime regDate;
    private String content;
}
