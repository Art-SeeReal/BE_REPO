package com.ArtSeeReal.pro.dto.user;

import com.ArtSeeReal.pro.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserPlannerDTO {
    private String userId;
    private UserType userType;
    private String nickname;
}
