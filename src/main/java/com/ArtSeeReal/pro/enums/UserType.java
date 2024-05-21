package com.ArtSeeReal.pro.enums;

import com.ArtSeeReal.pro.enums.enuminfo.UserInfo;
import lombok.Getter;

@Getter
public enum UserType {

    ADMIN("ADMIN","운영자"),
    AUTHOR("AUTHOR","작가"),
    PLANNER("PLANNER","기획자");

    private final String code;
    private final String label;

    UserType(String code,String label){
        this.code = code;
        this.label = label;
    }

    public UserInfo toUserInfo(){
        return new UserInfo(this.code,this.label);
    }
}
