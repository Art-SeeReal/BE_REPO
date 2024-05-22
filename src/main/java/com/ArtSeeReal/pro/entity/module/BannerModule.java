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
public abstract class BannerModule {
    @Column(length = 256, nullable = false)
    protected String imageUrl;
    @Column(length = 512, nullable = false)
    protected String linkUrl;
    @Column(nullable = false)
    protected LocalDateTime regDate;
}
