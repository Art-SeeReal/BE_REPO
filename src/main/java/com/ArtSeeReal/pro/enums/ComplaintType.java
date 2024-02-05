package com.ArtSeeReal.pro.enums;

import lombok.Getter;

@Getter
public enum ComplaintType {

    temp("temp",0);

    private final String name;
    private final int number;

    ComplaintType(String name, int number){
        this.name = name;
        this.number = number;
    }
}
