package com.ArtSeeReal.pro.entity.module;

import com.ArtSeeReal.pro.enums.CategoryType;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@NoArgsConstructor
@MappedSuperclass
@SuperBuilder
public abstract class PortfolioModule {
    @Column(length = 64,nullable = false)
    protected String userUid;
    @Column(nullable = false, columnDefinition = "BIGINT default 0")
    protected Long viewCnt;
    @Column(length = 128, nullable = false)
    protected String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    protected String content;
    @Column(nullable = false)
    protected CategoryType category;
    @Column(nullable = false)
    protected LocalDateTime regDate;
    @Column(length = 256)
    protected String thumbnail;
}
