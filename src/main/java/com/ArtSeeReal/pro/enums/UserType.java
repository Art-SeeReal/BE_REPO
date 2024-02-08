package com.ArtSeeReal.pro.enums;

import lombok.Getter;

@Getter
public enum UserType {

    ADMIN("admin","운영자",0),
    AUTHOR("author","작가",1),
    PLANNER("planner","기획자",2);

    private final String name;
    private final String korean;
    private final int number;

    UserType(String name,String korean, int number){
        this.name = name;
        this.korean = korean;
        this.number = number;
    }
}
