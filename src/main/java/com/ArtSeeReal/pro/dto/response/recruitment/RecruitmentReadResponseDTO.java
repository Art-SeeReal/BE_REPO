package com.ArtSeeReal.pro.dto.response.recruitment;

import com.ArtSeeReal.pro.dto.recruitment.RecruitmentReadDTO;
import com.ArtSeeReal.pro.enums.enuminfo.CategoryInfo;
import com.ArtSeeReal.pro.enums.enuminfo.RegionInfo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class RecruitmentReadResponseDTO {
    private final String id;
    private final String imageUrl;
    private final String title;
    private final String nickname;
    private final RegionInfo regions;
    private final CategoryInfo fields; // 별건 아닌데 단수로 바꿔야 하는거 아닌가? 하난데
    private final boolean isScrap;
    private final boolean isLike;
    private final String userId;
    private final Long view;
    private final LocalDateTime regDate;
    private final LocalDateTime dueDate;
    private final Long payment;
    private final String content;

    public RecruitmentReadResponseDTO(RecruitmentReadDTO dto){
        this.id = dto.getId();
        this.imageUrl = dto.getImageUrl();
        this.title = dto.getTitle();
        this.nickname = dto.getNickname();
        this.regions = dto.getRegion().toRegionInfo();
        this.fields = dto.getField().toCategoryInfo();
        this.isScrap = dto.isScrap();
        this.isLike = dto.isLike();
        this.userId = dto.getUserId();
        this.view = dto.getView();
        this.regDate = dto.getRegDate();
        this.dueDate = dto.getDueDate();
        this.payment = dto.getPayment();
        this.content = dto.getContent();
    }

    public RecruitmentReadResponseDTO(RecruitmentReadDTO dto, boolean isUserLike, boolean isRecruitmentScrap){
        this.id = dto.getId();
        this.imageUrl = dto.getImageUrl();
        this.title = dto.getTitle();
        this.nickname = dto.getNickname();
        this.regions = dto.getRegion().toRegionInfo();
        this.fields = dto.getField().toCategoryInfo();
        this.isScrap = isRecruitmentScrap;
        this.isLike = isUserLike;
        this.userId = dto.getUserId();
        this.view = dto.getView();
        this.regDate = dto.getRegDate();
        this.dueDate = dto.getDueDate();
        this.payment = dto.getPayment();
        this.content = dto.getContent();
    }
}
