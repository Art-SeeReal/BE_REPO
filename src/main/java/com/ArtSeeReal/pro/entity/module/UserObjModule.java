package com.ArtSeeReal.pro.entity.module;

import com.ArtSeeReal.pro.enums.ObjType;
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
public abstract class UserObjModule {
    @Column(length = 64,nullable = false)
    protected String userUid;

    @Column(length = 64,nullable = false)
    protected String targetUid;

    @Column(nullable = false)
    protected ObjType targetType;

}
