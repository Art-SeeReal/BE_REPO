package com.ArtSeeReal.pro.dto.response.portfoilo;

import com.ArtSeeReal.pro.dto.portfolio.PortfolioReadDTO;
import com.ArtSeeReal.pro.enums.enuminfo.CategoryInfo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PortfolioReadResponseDTO {
    private final String id;
    private final String imageUrl;
    private final String title;
    private final String nickname;
    private final CategoryInfo fields; // 별건 아닌데 단수로 바꿔야 하는거 아닌가? 하난데
    private final boolean isScrap;
    private final boolean isLike;
    private final String userId;
    private final Long view;
    private final LocalDateTime regDate;
    private final String content;

    public PortfolioReadResponseDTO(PortfolioReadDTO dto){
        this.id = dto.getId();
        this.imageUrl = dto.getImageUrl();
        this.title = dto.getTitle();
        this.nickname = dto.getNickname();
        this.fields = dto.getField().toCategoryInfo();
        this.isScrap = dto.isScrap();
        this.isLike = dto.isLike();
        this.userId = dto.getUserId();
        this.view = dto.getView();
        this.regDate = dto.getRegDate();
        this.content = dto.getContent();
    }

    public PortfolioReadResponseDTO(PortfolioReadDTO dto, boolean isUserLike, boolean isPortfolioScrap){
        this.id = dto.getId();
        this.imageUrl = dto.getImageUrl();
        this.title = dto.getTitle();
        this.nickname = dto.getNickname();
        this.fields = dto.getField().toCategoryInfo();
        this.isScrap = isPortfolioScrap;
        this.isLike = isUserLike;
        this.userId = dto.getUserId();
        this.view = dto.getView();
        this.regDate = dto.getRegDate();
        this.content = dto.getContent();
    }
}
