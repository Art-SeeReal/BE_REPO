package com.ArtSeeReal.pro.entity.history;

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

@Entity(name = "BOARD_HISTORY_TB")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardHistory {

    @Id
    @Column(length = 64,nullable = false)
    private String uid;

    @Column(length = 64,nullable = false)
    private String boardUid;

    @Column(length = 64,nullable = false)
    private String userUid;

    @Column(nullable = false, columnDefinition = "BIGINT default 0")
    private Long viewCnt;

    @Column(nullable = false)
    private BoardType boardType;

    @Column(length = 128, nullable = false)
    private String oldTitle;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String oldContent;

    @Column(nullable = false)
    private RegionType oldRegionType;

    @Column(nullable = false)
    private Long oldCategory;

    @Column(length = 256)
    private String oldThumbnail;

    @Column(length = 128, nullable = false)
    private String newTitle;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String newContent;

    @Column(nullable = false)
    private RegionType newRegionType;

    @Column(nullable = false)
    private Long newCategory;

    @Column(length = 256)
    private String newThumbnail;

    @Column(nullable = false)
    private LocalDateTime modDate;

    @Column(length = 64,nullable = false)
    private String modUserUid;

}
