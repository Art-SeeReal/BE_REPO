package com.ArtSeeReal.pro.entity.main;

import com.ArtSeeReal.pro.enums.BoardType;
import com.ArtSeeReal.pro.enums.RegionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "BOARD_TB")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {

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

    @Column(nullable = false)
    private BoardType boardType;

    @Column(length = 256)
    private String thumbnail;
}
