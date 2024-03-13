package com.ArtSeeReal.pro.entity.delete;

import com.ArtSeeReal.pro.enums.RegionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "PORTFOLIO_DELETE_TB")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PortfolioDelete {

    @Id
    @Column(length = 64,nullable = false)
    private String uid;

    @Column(length = 64,nullable = false)
    private String boardUid;

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

    @Column(length = 256)
    private String thumbnail;

    @Column(nullable = false)
    private LocalDateTime delDate;

    @Column(length = 64,nullable = false)
    private String delUserUid;
}