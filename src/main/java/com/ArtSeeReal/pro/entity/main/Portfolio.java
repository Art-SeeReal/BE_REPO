package com.ArtSeeReal.pro.entity.main;

import com.ArtSeeReal.pro.dto.response.portfoilo.PortfolioCreateResponseDTO;
import com.ArtSeeReal.pro.dto.portfolio.PortfolioInfoResponseDTO;
import com.ArtSeeReal.pro.dto.request.portfolio.PortfolioUpdateRequestDTO;
import com.ArtSeeReal.pro.entity.delete.PortfolioDelete;
import com.ArtSeeReal.pro.entity.module.PortfolioModule;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Entity(name = "PORTFOLIO_TB")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(indexes = {@Index(name = "idx_portfolio_title", columnList = "title")})
public class Portfolio extends PortfolioModule {
    @Id
    @Column(length = 64,nullable = false)
    private String uid;
    public PortfolioCreateResponseDTO toCreateResponseDTO() {
        return PortfolioCreateResponseDTO.builder()
                .uid(uid)
                .userUid(userUid)
                .viewCnt(viewCnt)
                .title(title)
                .content(content)
                .category(category)
                .regDate(regDate)
                .build();
    }
    public PortfolioInfoResponseDTO toReadResponseDTO() {
        return PortfolioInfoResponseDTO.builder()
                .uid(uid)
                .userUid(userUid)
                .viewCnt(viewCnt)
                .title(title)
                .content(content)
                .category(category)
                .regDate(regDate)
                .build();
    }
    public void updateFromDTO(PortfolioUpdateRequestDTO dto){
        title = dto.getTitle();
        content = dto.getContent();
        category = dto.getFields();
    }
    public PortfolioDelete toBoardDelete(String uid){
        return PortfolioDelete.builder()
                .uid(uid)
                .boardUid(this.uid)
                .userUid(userUid)
                .viewCnt(viewCnt)
                .title(title)
                .content(content)
                .category(category)
                .regDate(regDate)
                .thumbnail(thumbnail)
                .delDate(LocalDateTime.now())
                .build();
    }
}
