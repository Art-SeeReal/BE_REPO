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
public abstract class RequestCommentModule {
    @Column(length = 64,nullable = false)
    protected String userUid;
    @Column(length = 64,nullable = false)
    protected String postUid;
    @Column(nullable = false, columnDefinition = "TEXT")
    protected String content;
    @Column(nullable = false)
    protected LocalDateTime regDate;
}
