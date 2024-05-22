package com.ArtSeeReal.pro.enums;

import com.ArtSeeReal.pro.enums.enuminfo.RegionInfo;
import lombok.Getter;

@Getter
public enum RegionType {

    SEOUL("I000","서울"),
    BUSAN("H000","부산"),
    DAEGU("F000","대구"),
    GWANGJU("E000","광주"),
    INCHEON("K000","인천"),
    DAEJEON("G000","대전"),
    ULSAN("J000","울산"),
    SEJONG("1000","세종"),
    GYEONGGI("B000","경기"),
    GANGWON("A000","강원"),
    JEONNAM("L000","전남"),
    JEONBUK("M000","전북"),
    GYEONGNAM("C000","경남"),
    GYEONGBUK("D000","경북"),
    CHUNGNAM("O000","충남"),
    CHUNGBUK("P000","충북"),
    JEJU("N000","제주");

    private final String code;
    private final String label;

    RegionType(String code,String label){
        this.code = code;
        this.label = label;
    }

    public RegionInfo toRegionInfo(){
        return new RegionInfo(this.code,this.label);
    }
}


