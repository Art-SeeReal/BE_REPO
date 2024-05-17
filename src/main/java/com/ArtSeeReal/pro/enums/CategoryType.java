package com.ArtSeeReal.pro.enums;

public enum CategoryType {

    ART("A000","미술"),
    CRAFT("B000","공예"),
    DESIGN("C000","디자인"),
    PHOTO_VIDEO("D000","사진영상"),
    MUSIC("E000","음악"),
    LITERATURE_DANCE("F000","문학무용"),
    PLANNING("G000","기획");

    private final String code;
    private final String label;

    CategoryType(String code, String label){
        this.code = code;
        this.label = label;
    }
}
