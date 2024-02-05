package com.ArtSeeReal.pro.enums;

import lombok.Getter;

@Getter
public enum BoardType {

    PORTFOLIO("portfolio",0),
    ANNOUNCEMENT("Announcement",1);

    private final String name;
    private final int number;

    BoardType(String name, int number){
        this.name = name;
        this.number = number;
    }
}
