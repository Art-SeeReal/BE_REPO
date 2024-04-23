package com.ArtSeeReal.pro.entity.module;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Getter
@ToString
@NoArgsConstructor
@MappedSuperclass
@SuperBuilder
public abstract class IntroduceModule {
    @Column(length = 64,nullable = false)
    protected String userUid;
    @Column(nullable = false, columnDefinition = "TEXT")
    protected String content;
}
