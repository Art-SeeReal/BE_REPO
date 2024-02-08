package com.ArtSeeReal.pro.enums;

import lombok.Getter;

@Getter
public enum BoardType {

    PORTFOLIO("portfolio","포트폴리오",0),
    RECRUITMENT("recruitment","기획자공고",1);

    private final String name;
    private final String korean;
    private final int number;

    BoardType(String name,String korean, int number){
        this.name = name;
        this.korean = korean;
        this.number = number;
    }
}
