package com.ArtSeeReal.pro.enums;

import lombok.Getter;

@Getter
public enum ComplaintType {

    ABUSE("abuse","욕설",0),
    CRITICISM("criticism","비방",1),
    ADVERTISING("advertising","광고",2),
    ETC("etc","기타",3);

    private final String name;
    private final String korean;
    private final int number;

    ComplaintType(String name,String korean, int number){
        this.name = name;
        this.korean = korean;
        this.number = number;
    }
}
