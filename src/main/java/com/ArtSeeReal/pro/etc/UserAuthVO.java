package com.ArtSeeReal.pro.etc;

import com.ArtSeeReal.pro.enums.system.SystemConstantEnum;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class UserAuthVO {

    private final String userUid;
    private final LocalDateTime authTime;

    public UserAuthVO(String userUid) {
        this.userUid = userUid;
        this.authTime = LocalDateTime.now().plusMinutes(SystemConstantEnum.VALID_TIME.getNumber());
    }
}
