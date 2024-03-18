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
public abstract class FileModule {
    @Column(length = 64,nullable = false)
    protected String targetUid;

    @Column(length = 256, nullable = false)
    protected String url;
}
