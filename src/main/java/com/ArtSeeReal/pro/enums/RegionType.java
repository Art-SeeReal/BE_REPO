package com.ArtSeeReal.pro.enums;

import lombok.Getter;

@Getter
public enum RegionType {

    SEOUL("Seoul","서울",0),
    BUSAN("Busan","부산",1),
    DAEGU("Daegu","대구",2),
    GWANGJU("Gwangju","광주",3),
    INCHEON("incheon","인천",4),
    DAEJEON("Daejeon","대전",5),
    ULSAN("Ulsan","울산",6),
    GYEONGGI("Gyeonggi","경기",7),
    JEONNAM("Jeonnam","전남",8),
    JEONBUK("Jeonbuk","전북",9),
    GYEONGNAM("Gyeongnam","경남",10),
    GYEONGBUK("Gyeongbuk","경북",11),
    CHUNGNAM("Chungnam","충남",12),
    CHUNGBUK("Chungbuk","충북",13),
    JEJU("Jeju","제주",14);

    private final String name;
    private final String korean;
    private final int number;

    RegionType(String name,String korean, int number){
        this.name = name;
        this.korean = korean;
        this.number = number;
    }
}
