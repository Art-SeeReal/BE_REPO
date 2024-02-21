package com.ArtSeeReal.pro.etc;


import com.ArtSeeReal.pro.repository.module.ModuleRepository;
import java.util.UUID;


public class Uid {
    private static String uidCreator(){
            return UUID
                    .randomUUID()
                    .toString()
                    .replaceAll("-","");
    }

    public static String uidCreator(ModuleRepository repository){
        boolean duplicate = true;
        String uid = uidCreator();

        while (duplicate) {
            if(repository.existsByUid(uid))
                uid = uidCreator();
            else
                duplicate = false;
        }

        return uid;
    }
}
