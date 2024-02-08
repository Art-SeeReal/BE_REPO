package com.ArtSeeReal.pro.etc;


import java.util.UUID;


public class Uid {
    public static String UidCreator(){
            return UUID
                    .randomUUID()
                    .toString()
                    .replaceAll("-","");
    }
}
