package com.ArtSeeReal.pro.entity.history;

import com.ArtSeeReal.pro.entity.module.PortfolioModule;
import com.ArtSeeReal.pro.enums.RegionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity(name = "PORTFOLIO_HISTORY_TB")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PortfolioHistory extends PortfolioModule {

    @Id
    @Column(length = 64,nullable = false)
    private String uid;

    @Column(length = 64,nullable = false)
    private String boardUid;

    @Column(length = 128, nullable = false)
    private String exTitle;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String exContent;

    @Column(nullable = false)
    private RegionType exRegionType;

    @Column(nullable = false)
    private Long exCategory;

    @Column(length = 256)
    private String exThumbnail;

    @Column(nullable = false)
    private LocalDateTime modDate;

    @Column(length = 64,nullable = false)
    private String modUserUid;

}
