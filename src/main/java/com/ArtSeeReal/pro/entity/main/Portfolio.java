package com.ArtSeeReal.pro.entity.main;

import com.ArtSeeReal.pro.dto.portfolio.PortfolioCreateResponseDTO;
import com.ArtSeeReal.pro.dto.portfolio.PortfolioReadResponseDTO;
import com.ArtSeeReal.pro.dto.portfolio.PortfolioUpdateRequestDTO;
import com.ArtSeeReal.pro.entity.delete.PortfolioDelete;
import com.ArtSeeReal.pro.enums.RegionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "PORTFOLIO_TB")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Portfolio {

    @Id
    @Column(length = 64,nullable = false)
    private String uid;

    @Column(length = 64,nullable = false)
    private String userUid;

    @Column(nullable = false, columnDefinition = "BIGINT default 0")
    private Long viewCnt;

    @Column(length = 128, nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private RegionType regionType;

    @Column(nullable = false)
    private Long category;

    @Column(nullable = false)
    private LocalDateTime regDate;

    // TODO : 아마 바이트타입으로 바꿀 필요 있을 듯
    @Column(length = 256)
    private String thumbnail;

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
