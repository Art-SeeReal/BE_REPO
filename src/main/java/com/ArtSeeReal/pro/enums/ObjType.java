package com.ArtSeeReal.pro.enums;

import lombok.Getter;

@Getter
public enum ObjType {

    AUTHOR("author","작가",0),
    PLANNER("planner","기획자",1),
    LIKE("like","포트폴리오 좋아요",2),
    FAVORITES("favorites","공고 즐겨찾기",3),
    APPLY("apply","공고 지원",4);

    private final String name;
    private final String korean;
    private final int number;

    ObjType(String name,String korean, int number){
        this.name = name;
        this.korean = korean;
        this.number = number;
    }
}
