package com.ArtSeeReal.pro.entity.main;

import com.ArtSeeReal.pro.dto.portfolio.PortfolioCreateResponseDTO;
import com.ArtSeeReal.pro.dto.portfolio.PortfolioReadResponseDTO;
import com.ArtSeeReal.pro.dto.portfolio.PortfolioUpdateRequestDTO;
import com.ArtSeeReal.pro.entity.delete.PortfolioDelete;
import com.ArtSeeReal.pro.entity.module.PortfolioModule;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity(name = "PORTFOLIO_TB")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
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
                .regionType(regionType)
                .category(category)
                .regDate(regDate)
                .build();
    }

    public PortfolioReadResponseDTO toReadResponseDTO() {
        return PortfolioReadResponseDTO.builder()
                .uid(uid)
                .userUid(userUid)
                .viewCnt(viewCnt)
                .title(title)
                .content(content)
                .regionType(regionType)
                .category(category)
                .regDate(regDate)
                .build();
    }

    public void updateFromDTO(PortfolioUpdateRequestDTO dto){
        title = dto.getTitle();
        content = dto.getContent();
        regionType = dto.getRegionType();
        category = dto.getCategory();
    }

    public PortfolioDelete toBoardDelete(String uid, String delUserUid){
        return PortfolioDelete.builder()
                .uid(uid)
                .boardUid(this.uid)
                .userUid(userUid)
                .viewCnt(viewCnt)
                .title(title)
                .content(content)
                .regionType(regionType)
                .category(category)
                .regDate(regDate)
                .thumbnail(thumbnail)
                .delDate(LocalDateTime.now())
                .delUserUid(delUserUid)
                .build();
    }
}
