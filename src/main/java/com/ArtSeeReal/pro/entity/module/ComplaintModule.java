package com.ArtSeeReal.pro.entity.module;

import com.ArtSeeReal.pro.enums.ComplaintType;
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
public abstract class ComplaintModule {
    @Column(length = 64,nullable = false)
    protected String userUid;
    @Column(length = 64,nullable = false)
    protected String targetUid;
    @Column(nullable = false)
    protected ComplaintType type;
    @Column(nullable = false)
    protected LocalDateTime regDate;
}
