package com.ArtSeeReal.pro.dto.with;

import com.ArtSeeReal.pro.dto.portfolio.PortfolioReadResponseDTO;
import com.ArtSeeReal.pro.entity.main.Portfolio;
import com.ArtSeeReal.pro.entity.main.User;
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
public class PortfolioWithUserDTO {
    private Portfolio portfolio;
    private User user;

    public PortfolioReadResponseDTO toReadResponseDTO() {
        return PortfolioReadResponseDTO.builder()
                .uid(portfolio.getUid())
                .userUid(user.getUid())
                .nickname(user.getNickname())
                .viewCnt(portfolio.getViewCnt())
                .title(portfolio.getTitle())
                .content(portfolio.getContent())
                .regionType(portfolio.getRegionType())
                .category(portfolio.getCategory())
                .regDate(portfolio.getRegDate())
                .build();
    }

}
