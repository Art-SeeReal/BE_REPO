package com.ArtSeeReal.pro.entity.main;

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

    @Column(nullable = false, columnDefinition = "0")
    private Long viewCnt;

    @Column(length = 128, nullable = false)
    private String title;

    @Column(length = 8196, nullable = false)
    private String content;

    @Column(nullable = false)
    private Long region;

    @Column(nullable = false)
    private Long category;

    @Column(nullable = false)
    private LocalDateTime regDate;

    @Column(nullable = false)
    private Long boardType;

    @Column(length = 256)
    private String thumbnail;
}
