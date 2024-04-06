package com.ArtSeeReal.pro.enums.system;

import lombok.Getter;

@Getter
public enum SystemConstantEnum {

    VALID_TIME(5L),
    ID_REFERENCE_POINT(8L),
    ID_MINIMUM_LENGTH(5L),;

    public final Long number;

    SystemConstantEnum(Long number) {
        this.number = number;
    }
}
