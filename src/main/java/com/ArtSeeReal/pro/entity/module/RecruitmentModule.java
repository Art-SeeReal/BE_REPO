package com.ArtSeeReal.pro.entity.module;

import com.ArtSeeReal.pro.enums.RegionType;
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
public abstract class RecruitmentModule {
    @Column(length = 64,nullable = false)
    protected String userUid;

    @Column(nullable = false, columnDefinition = "BIGINT default 0")
    protected Long viewCnt;

    @Column(length = 128, nullable = false)
    protected String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    protected String content;

    @Column(nullable = false)
    protected RegionType regionType;

    @Column(nullable = false)
    protected Long category;

    @Column(nullable = false)
    protected LocalDateTime regDate;

    // TODO : 아마 바이트타입으로 바꿀 필요 있을 듯
    @Column(columnDefinition = "TEXT")
    protected String thumbnail;

    @Column(nullable = false)
    protected LocalDateTime dueDate;

}
