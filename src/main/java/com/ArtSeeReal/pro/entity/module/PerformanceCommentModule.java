package com.ArtSeeReal.pro.entity.module;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@ToString
@NoArgsConstructor
@MappedSuperclass
@SuperBuilder
public abstract class PerformanceCommentModule {
    @Column(nullable = false,length = 64)
    protected String UserUid;
    @Column(nullable = false)
    protected String targetUid;
    @Column(nullable = false,length = 512)
    protected String comment;
    @Column(nullable = false)
    protected LocalDateTime regDate;
}
