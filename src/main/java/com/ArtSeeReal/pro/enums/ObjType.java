package com.ArtSeeReal.pro.enums;

import lombok.Getter;

@Getter
public enum ObjType {

    AUTHOR("author",0),
    PLANNER("planner",1),
    LIKE("like",2),
    FAVORITES("favorites",3),
    APPLY("apply",4);

    private final String name;
    private final int number;

    ObjType(String name, int number){
        this.name = name;
        this.number = number;
    }
}
